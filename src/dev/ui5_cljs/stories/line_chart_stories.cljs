(ns ui5-cljs.stories.line-chart-stories
  (:require
   [reagent.core :as r]
   [ui5-cljs.stories.utils :as utils]
   [ui5-cljs.line-chart :refer [line-chart]]))


(def ^:export default
  (utils/->default
   {:title     "Line chart"
    :component line-chart
    :argTypes  {:dimensions          {:type        {:name "collection" :required true}
                                      :description "An array of config objects. Can be an reagent atom.
                                                    Each object will define one dimension of the chart.
                                                    Required Properties
                                                    - accessor: string containing the path to the dataset key the dimension should display.
                                                    Supports object structures by using 'parent.child'. Can also be a getter.
                                                    Optional Properties
                                                    - formatter: function will be called for each data label and allows you to format it according to your needs
                                                    - interval: number that controls how many ticks are rendered on the x axis"
                                      :control     {:type nil}}
                :measures            {:type        {:name "collection" :required true}
                                      :description "An array of config objects. Can be an reagent atom.
                                                    Each object is defining one line in the chart.
                                                    Required properties
                                                    - accessor: string containing the path to the dataset key this line should display.
                                                    Supports object structures by using 'parent.child'. Can also be a getter.
                                                    Optional properties
                                                    - label: Label to display in legends or tooltips. Falls back to the accessor if not present.
                                                    - color: any valid CSS Color or CSS Variable. Defaults to the sapChart_Ordinal colors
                                                    - formatter: function will be called for each data label and allows you to format it according to your needs
                                                    - hide-data-label: flag whether the data labels should be hidden in the chart for this line.
                                                    - data-label: a custom component to be used for the data label
                                                    - width: line width, defaults to 1
                                                    - opacity: line opacity, defaults to 1
                                                    - show-dot: Flag whether the line dot should be displayed or not.
                                                    - line-config: This prop allows passing all Line Properties of the Recharts library."
                                      :control     {:type nil}}
                :dataset             {:type        {:name "collection" :required false}
                                      :description "The dataset is an array of object which will be displayed in the chart.
                                                    Can be an reagent atom."
                                      :control     {:type nil}}
                :sync-id             {:type        {:name "string" :required false}
                                      :description "A string which defines an id to synchronize two separate charts.
                                                    Can be an reagent atom.
                                                    Charts with the same syncId synchronize the position of the tooltips,
                                                    the startIndex and the endIndex of the zooming tool."
                                      :control     "text"}
                :chart-config        {:type        {:name "map" :required false}
                                      :description "Defines possible configurations of the chart.
                                                    Properties available on all charts:
                                                    - margin: Sets the margin of the chart container. Receives a object with four possible properties (right, left, top, bottom) that expect a number as value.
                                                    - legend-position: Vertical position of the legend. Can be one of the following: \"top\",\"middle\", \"bottom\" (\"middle\" is not supported for: ColumnChartWithTrend, DonutChart, PieChart)
                                                    - legend-horizontal-align: Alignment of the legend. Can be one of the following: \"left\", \"center\", \"right\"
                                                    - resize-debounce: Number that sets the amount of delay time the chart waits when resizing.
                                                    - reference-line: Draws the reference line in the chart. Receives an object with the following properties: color, label, value"
                                      :control     {:type nil}}
                :tooltip-config      {:type        {:name "map" :required false}
                                      :description "Defines the configuration object for the internally used recharts Tooltip popover that is displayed when hovering over data points.
                                                    You can find all possible configuration properties here (https://recharts.org/en-US/api/Tooltip).
                                                    (filter-null item-style wrapper-style content-style label-style allow-escape-view-box view-box label-formatter item-sorter is-animation-active animation-duration animation-easing)
                                                    Note: It is possible to overwrite internally used tooltip props, so use with caution!"
                                      :control     {:type nil}}
                :chart-placeholder   {:type        {:name "component" :required false}
                                      :description "Reagent component. Injects a custom loading placeholder which is used when no data are available.
                                                    If the property isn't set the standard loading placeholder of the specific chart is used."
                                      :control     {:type nil}}
                :loading             {:type         {:name "boolean" :required false}
                                      :description  "Flag whether the chart should display a loading indicator.
                                                     Can be an reagent atom.
                                                     This can either be a placeholder shimmer (in case the chart has no data yet) or a small loading bar in the top of the chart (in case the chart has already some data to display)."
                                      :defaultValue false
                                      :control      "boolean"}
                :no-legend           {:type         {:name "boolean" :required false}
                                      :description  "toggles the visibility of the legend below the chart. If this prop is true, no legend will be rendered."
                                      :defaultValue false
                                      :control      "boolean"}
                :no-animation        {:type         {:name "boolean" :required false}
                                      :description  "disables all chart animations when set to true."
                                      :defaultValue false
                                      :control      "boolean"}
                :on-click            {:type        {:name "function" :required false}
                                      :description "Fired when clicked anywhere in the chart. Receives the click event as a parameter."
                                      :control     {:type nil}}
                :on-data-point-click {:type        {:name "function" :required false}
                                      :description "The onDataPointClick event fires whenever the user clicks on e.g. a bar in BarChart or a point the LineChart.
                                                    You can use this event to trigger e.g. navigations or set filters based on the last clicked data point.
                                                    Receives the dataKey and the payload as parameters. The dataKey is the key of the clicked data point and the payload is the data of the clicked data point."
                                      :control     {:type nil}}
                :on-legend-click     {:type        {:name "function" :required false}
                                      :description "The onLegendClick event fires when the user clicks on a label of the legend.
                                                    This can be useful to e.g. show/hide the current dataset in case you have multiple datasets in in your chart.
                                                    Receives the dataKey as a parameter. The dataKey is the key of the clicked data point."
                                      :control     {:type nil}}
                :style               {:type        {:name "map" :required false}
                                      :description "Element style which will be appended to the most outer element of a component. Use this prop carefully, some css properties might break the component."
                                      :control     {:type nil}}
                :class               {:type        {:name "string" :required false}
                                      :description "CSS Class Name which will be appended to the most outer element of a component. Use this prop carefully, overwriting CSS rules might break the component."
                                      :control     "text"}}}))


(def sample-dataset
  [{:name "January", :sessions 300, :users 100, :volume 756},
   {:name "February", :sessions 330, :users 230, :volume 880},
   {:name "March", :sessions 404, :users 240, :volume 700},
   {:name "April", :sessions 80, :users 280, :volume 604},
   {:name "May", :sessions 300, :users 100, :volume 756},
   {:name "June", :sessions 330, :users 230, :volume 880},
   {:name "July", :sessions 470, :users 20, :volume 450},
   {:name "August", :sessions 180, :users 220, :volume 104},
   {:name "September", :sessions 360, :users 200, :volume 879},
   {:name "October", :sessions 500, :users 250, :volume 200},
   {:name "November", :sessions 404, :users 240, :volume 700},
   {:name "December", :sessions 80, :users 280, :volume 604}])


(defn ^:export line-chart-basic [args]
  (r/as-element
   [line-chart
    {:dimensions [{:accessor "name"
                   :interval 0}]
     :measures   [{:accessor    "users"
                   :label       "Users"
                   :line-config {:type "linear"}}
                  {:accessor        "sessions"
                   :hide-data-label true
                   :label           "Active Sessions"}
                  {:accessor "volume"
                   :label    "Vol."}]
     :dataset    sample-dataset}]))


(defn ^:export line-chart-custom-placeholder [args]
  (r/as-element
   [line-chart
    {:chart-placeholder [:h3 "Loading.."]
     :dimensions        []
     :measures          []}]))


(defn ^:export line-chart-with-handlers [args]
  (r/as-element
   [line-chart
    {:dimensions          [{:accessor "name"
                            :interval 0}]
     :measures            [{:accessor    "users"
                            :label       "Users"
                            :line-config {:type "linear"}}
                           {:accessor        "sessions"
                            :hide-data-label true
                            :label           "Active Sessions"}
                           {:accessor "volume"
                            :label    "Vol."}]
     :dataset             sample-dataset
     :on-click            (fn [event]
                            (js/console.log "Click event" event))
     :on-data-point-click (fn [data-key payload]
                            (js/console.log "Data point click event" data-key payload))
     :on-legend-click     (fn [data-key]
                            (js/console.log "Legend click event" data-key))}]))


(defn ^:export line-chart-custom-color [args]
  (r/as-element
   [line-chart
    {:dimensions [{:accessor "name"}]
     :measures   [{:accessor "users" :color "red"}]
     :dataset    [{:name "January", :users 100},
                  {:name "February", :users 230},
                  {:name "March", :users 240},
                  {:name "April", :users 280},
                  {:name "May", :users 100},
                  {:name "June", :users 230},
                  {:name "July", :users 20},
                  {:name "August", :users 220},
                  {:name "September", :users 200},
                  {:name "October", :users 250},
                  {:name "November", :users 240},
                  {:name "December", :users 280}]}]))


(defn ^:export line-chart-formatters [args]
  (r/as-element
   [line-chart
    {:dimensions [{:accessor  "name"
                   :formatter (fn [value]
                                (str "Month " value))}]
     :measures   [{:accessor "users"
                   :label    "number of users"}
                  {:accessor  "sessions"
                   :formatter (fn [value]
                                (str "Sessions " value))}
                  {:accessor "volume"
                   :label    "Vol."}]
     :dataset    sample-dataset}]))


(defn ^:export line-chart-ref-line [args]
  (r/as-element
   [line-chart
    {:dimensions   [{:accessor  "name"
                     :formatter (fn [value]
                                  (str "Month " value))}]
     :measures     [{:accessor "users"
                     :label    "number of users"}
                    {:accessor  "sessions"
                     :formatter (fn [value]
                                  (str "Sessions " value))}
                    {:accessor "volume"
                     :label    "Vol."}]
     :dataset      sample-dataset
     :chart-config {:reference-line {:color "red"
                                     :label "MAX"
                                     :value 650}}}]))


(defn ^:export line-chart-linear-gradient [args]
  (r/as-element
   [line-chart
    {:dimensions [{:accessor "name"}]
     :measures   [{:accessor "users"
                   :color    "url(#colorUsers)"
                   :width    2}]
     :dataset    sample-dataset}
    [:defs
     [:linearGradient#colorUsers {:x1 "0%" :x2 "0%" :y1 "0%" :y2 "100%"}
      [:stop {:offset "0%" :stopColor "red"}]
      [:stop {:offset "100%" :stopColor "green"}]]]]))

