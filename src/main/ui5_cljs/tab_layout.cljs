(ns ui5-cljs.tab-layout
  (:require
   [applied-science.js-interop :as j]
   [reagent.core :as r]
   [ui5-cljs.utils :as utils]
   ["@ui5/webcomponents-react" :refer [TabContainer Tab TabSeparator]]))


(def tab-separator
  (r/adapt-react-class TabSeparator))


(defn tab
  [{:keys [id sub-tabs text additional-text design icon disabled selected]}]
  (let [component (r/current-component)
        children  (r/children component)
        {:keys [slot]} (utils/component-props component)]
    (into
      [:> Tab (utils/assoc-some {}
                :data-id id
                :slot slot
                :subTabs (when (seq sub-tabs)
                           (utils/value->element
                            (into [:<>]
                              (map (fn [{:keys [content] :as tab-props}]
                                     [tab tab-props content]))
                              sub-tabs)))
                :text (utils/model->value text)
                :additionalText additional-text
                :design design
                :icon icon
                :disabled (utils/model->value disabled)
                :selected (utils/model->value selected))]
      children)))


(defn tab-layout
  [{:keys [layout content-background-design header-background-design overflow-button
           start-overflow-button on-tab-select collapsed fixed show-overflow tabs-overflow-mode
           style class]}]
  (let [component (r/current-component)
        children  (r/children component)]
    (into
      [:> TabContainer (utils/assoc-some {}
                         :tabLayout layout
                         :contentBackgroundDesign content-background-design
                         :headerBackgroundDesign header-background-design
                         :tabsOverflowMode tabs-overflow-mode
                         :overflowButton (utils/value->element overflow-button)
                         :startOverflowButton (utils/value->element start-overflow-button)
                         :onTabSelect (when (fn? on-tab-select)
                                        (fn [event]
                                          (let [tab   (j/get-in event [:detail :tab])
                                                index (j/get-in event [:detail :tabIndex])
                                                id    (j/get-in tab [:dataset :id])]
                                            (->> (utils/assoc-some {:index index}
                                                   :id id)
                                                 (on-tab-select event)))))
                         :collapsed (utils/model->value collapsed)
                         :fixed (utils/model->value fixed)
                         :showOverflow show-overflow
                         :style style
                         :className class)]
      children)))