(ns ui5-cljs.line-chart
  (:require
   [applied-science.js-interop :as j]
   [reagent.core :as r]
   [ui5-cljs.utils :as utils]
   ["@ui5/webcomponents-react-charts" :refer [LineChart]]))


(defn ->measures-map
  [measures]
  (mapv (fn [{:keys [line-config hide-data-label data-label show-dot] :as m}]
          (cond-> m
            line-config (assoc :lineConfig line-config)
            hide-data-label (dissoc :hideDataLabel hide-data-label)
            data-label (assoc :DataLabel data-label)
            show-dot (assoc :showDot show-dot)))
        measures))


(defn ->chart-config-map
  [{:keys [legend-position legend-horizontal-align resize-debounce reference-line] :as chart-config}]
  (cond-> chart-config
    legend-position (assoc :legendPosition legend-position)
    legend-horizontal-align (dissoc :legendHorizontalAlign legend-horizontal-align)
    resize-debounce (assoc :resizeDebounce resize-debounce)
    reference-line (assoc :referenceLine reference-line)))


(defn ->tooltip-config-map
  [{:keys [filter-null item-style wrapper-style content-style label-style allow-escape-view-box
           view-box label-formatter item-sorter is-animation-active animation-duration animation-easing]
    :as   tooltip-config}]
  (cond-> tooltip-config
    filter-null (assoc :filterNull filter-null)
    item-style (dissoc :itemStyle item-style)
    wrapper-style (assoc :wrapperStyle wrapper-style)
    content-style (assoc :contentStyle content-style)
    label-style (assoc :labelStyle label-style)
    view-box (assoc :viewBox view-box)
    allow-escape-view-box (assoc :allowEscapeViewBox allow-escape-view-box)
    label-formatter (assoc :labelFormatter label-formatter)
    item-sorter (assoc :itemSorter item-sorter)
    is-animation-active (assoc :isAnimationActive is-animation-active)
    animation-duration (assoc :animationDuration animation-duration)
    animation-easing (assoc :animationEasing animation-easing)))


(defn line-chart
  [{:keys [dimensions measures sync-id dataset chart-config
           tooltip-config chart-placeholder loading no-legend no-animation
           on-click on-data-point-click on-legend-click style class]}]
  (let [component (r/current-component)
        children  (r/children component)]
    (into
      [:> LineChart (utils/assoc-some {:dimensions (-> dimensions utils/model->value clj->js)
                                       :measures   (-> measures utils/model->value ->measures-map clj->js)}
                      :syncId (utils/model->value sync-id)
                      :dataset (-> dataset utils/model->value clj->js)
                      :chartConfig (-> chart-config ->chart-config-map clj->js)
                      :tooltipConfig (-> tooltip-config ->tooltip-config-map clj->js)
                      :ChartPlaceholder (when (some? chart-placeholder)
                                          (fn [& _]
                                            (utils/value->element chart-placeholder)))
                      :loading (utils/model->value loading)
                      :noLegend no-legend
                      :noAnimation no-animation
                      :onClick on-click
                      :onDataPointClick (when (fn? on-data-point-click)
                                          (fn [event]
                                            (let [data-key (j/get-in event [:detail :dataKey])
                                                  payload  (js->clj (j/get-in event [:detail :payload]))]
                                              (on-data-point-click data-key payload))))
                      :onLegendClick (when (fn? on-legend-click)
                                       (fn [event]
                                         (let [data-key (j/get-in event [:detail :dataKey])]
                                           (on-legend-click data-key))))
                      :style style
                      :className class)]
      children)))
