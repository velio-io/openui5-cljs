(ns ui5-cljs.stories.table-stories
  (:require
   [reagent.core :as r]
   [ui5-cljs.stories.utils :as utils]
   [ui5-cljs.table :refer [table]]))


(def ^:export default
  (utils/->default
   {:title     "Table"
    :component table
    :argTypes  {:rows                   {:type        {:name "collection" :required false}
                                         :description "Defines the component rows.
                                                       Each row is a map with the keys representing the column names.
                                                       Additionally, the row can have a `row/props` key with the properties to be applied to the row.
                                                       For interactive rows (clickable, selectable), the `row/props` key should contain the `id` property.
                                                       Which should be a string"
                                         :control     {:type nil}}
                :columns                {:type        {:name "collection" :required true}
                                         :description "Defines the configuration for the columns of the component.
                                                       Each column should contain a `name` key with the column name.
                                                       Additionally, the column can have the following keys:
                                                       - `demand-popin` (boolean): According to your minWidth settings, the component can be hidden in different screen sizes. Setting this property to true, shows this column as pop-in instead of hiding it.
                                                       - `popin-display` (string): Defines how the popin row is displayed. Could be \"Block\" or \"Inline\"
                                                       - `popin-text` (string): The text for the column when it pops in.
                                                       - `min-width` (number): Defines the minimum table width required to display this column. By default it is always displayed. The responsive behavior of the Table is determined by this property. As an example, by setting minWidth property to 400 sets the minimum width to 400 pixels, and shows this column on tablet (and desktop) but hides it on mobile.
                                                       - `content` (component): The content to display in the column. Could be a string, number, or a Reagent component (hiccup).
                                                       - `style` (object): Style object to apply to the column
                                                       - `class` (string): Class name to apply to the column"
                                         :control     {:type nil}}
                :growing                {:type        {:name "string" :required false}
                                         :description "Defines whether the table will have growing capability either by pressing a More button, or via user scroll.
                                                       In both cases load-more event is fired.
                                                       Available options:
                                                       Button - Shows a More button at the bottom of the table, pressing of which triggers the load-more event.
                                                       Scroll - The load-more event is triggered when the user scrolls to the bottom of the table;
                                                       None (default) - The growing is off."
                                         :control     "select"
                                         :options     ["Button" "Scroll" "None"]}
                :busy                   {:type        {:name "boolean" :required false}
                                         :description "Defines if the table is in busy state.
                                                       In this state the component's opacity is reduced and busy indicator is displayed at the bottom of the table."
                                         :control     "boolean"}
                :busy-delay             {:type        {:name "number" :required false}
                                         :description "Defines the delay in milliseconds, after which the busy indicator will show up for this component."
                                         :control     "number"}
                :hide-no-data           {:type        {:name "boolean" :required false}
                                         :description "Defines if the value of noDataText will be diplayed when there is no rows present in the table."
                                         :control     "boolean"}
                :sticky-column-header   {:type        {:name "boolean" :required false}
                                         :description "Determines whether the column headers remain fixed at the top of the page during vertical scrolling
                                                       as long as the Web Component is in the viewport.
                                                       Restrictions:
                                                       Browsers that do not support this feature:
                                                       - Internet Explorer
                                                       - Microsoft Edge lower than version 41 (EdgeHTML 16)
                                                       - Mozilla Firefox lower than version 59
                                                       Scrolling behavior:
                                                       If the Web Component is placed in layout containers that have the overflow: hidden or overflow: auto style definition,
                                                       this can prevent the sticky elements of the Web Component from becoming fixed at the top of the viewport."
                                         :control     "boolean"}
                :mode                   {:type        {:name "string" :required false}
                                         :description "Defines the mode of the component."
                                         :control     "select"
                                         :options     ["SingleSelect" "MultiSelect" "None"]}
                :no-data-text           {:type        {:name "string" :required false}
                                         :description "Defines the text that will be displayed when there is no data and hideNoData is not present."
                                         :control     "text"}
                :growing-button-subtext {:type        {:name "string" :required false}
                                         :description "Defines the subtext that will be displayed under the growingButtonText.
                                                       Note: This property takes effect if growing is set to Button."
                                         :control     "text"}
                :growing-button-text    {:type        {:name "string" :required false}
                                         :description "Defines the text that will be displayed inside the growing button at the bottom of the table, meant for loading more rows upon press.
                                                       Note: If not specified a built-in text will be displayed.
                                                       Note: This property takes effect if growing is set to Button."
                                         :control     "text"}
                :on-load-more           {:type        {:name "function" :required false}
                                         :description "Fired when the user presses the More button or scrolls to the table's end.
                                                       Note: The event will be fired if growing is set to Button or Scroll."
                                         :control     {:type nil}}
                :on-popin-change        {:type        {:name "function" :required false}
                                         :description "Fired when TableColumn is shown as a pop-in instead of hiding it.
                                                       Function will receive a vector of the currently popped-in columns."
                                         :control     {:type nil}}
                :on-row-click           {:type        {:name "function" :required false}
                                         :description "Fired when a row in Active mode is clicked or Enter key is pressed.
                                                       Function will receive the row map as a parameter."
                                         :control     {:type nil}}
                :on-selection-change    {:type        {:name "function" :required false}
                                         :description "Fired when selection is changed by user interaction in SingleSelect and MultiSelect modes.
                                                       Function will receive a vector of the selected rows."
                                         :control     {:type nil}}
                :accessible-name        {:type        {:name "string" :required false}
                                         :description "Accessible name for the table"
                                         :control     "text"}
                :accessible-name-ref    {:type        {:name "string" :required false}
                                         :description "Accessible name reference for the table"
                                         :control     "text"}
                :style                  {:type        {:name "object" :required false}
                                         :description "Style object to apply to the table"
                                         :control     {:type nil}}
                :class                  {:type        {:name "string" :required false}
                                         :description "Class name to apply to the table"
                                         :control     "text"}}}))


(def rows
  (r/atom [{:product    [:span "Notebook Basic"]
            :supplier   [:span "Very Best Screens"]
            :dimensions [:span "30 x 18 x 3cm"]
            :weight     "4.2KG"
            :price      "$956EUR"
            :row/props  {:id "123"}}
           {:product    [:span "Notebook Basic 17HT-1001"]
            :supplier   [:span "Very Best Screens"]
            :dimensions [:span "29 x 17 x 3.1cm"]
            :weight     "4.5KG"
            :price      "1249EUR"
            :row/props  {:id "456" :type "Active"}}]))


(def columns
  (r/atom [{:name    :product
            :style   {:width "12rem"}
            :content [:span "Product"]}
           {:name       :supplier
            :min-width  800
            :popin-text "Supplier"
            :content    [:span "Supplier"]}
           {:name         :dimensions
            :demand-popin true
            :min-width    600
            :popin-text   "Dimensions"
            :content      [:span "Dimensions"]}
           {:name         :weight
            :demand-popin true
            :min-width    600
            :popin-text   "Weight"
            :content      [:span "Weight"]}
           {:name    :price
            :content [:span "Price"]}]))


(defn ^:export table-basic [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [table (merge {:rows            rows
                    :columns         columns
                    :on-popin-change (fn [popped-columns]
                                       (println popped-columns))
                    :on-row-click    (fn [row]
                                       (println row))}
                   params)])))


(defn ^:export table-selected-rows [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [table (merge {:rows                rows
                    :columns             columns
                    :mode                "MultiSelect"
                    :on-selection-change (fn [selected-rows]
                                           (println selected-rows))}
                   params)])))


(defn create-rows [offset]
  (-> (for [index (range 25)]
        {:index       (str (+ offset index))
         :placeholder "Placeholder"})
      (vec)))


(def growing-rows
  (r/atom (create-rows 1)))


(defn load-more [_event]
  (let [offset (count @growing-rows)]
    (swap! growing-rows #(concat % (create-rows (inc offset))))))


(defn ^:export table-growing [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [:div {:style {:height   "250px"
                    :overflow "auto"}}
      [table {:rows         growing-rows
              :columns      [{:name    :index
                              :content [:span "Index"]}
                             {:name    :placeholder
                              :content [:span "Placeholder"]}]
              :on-load-more load-more
              :growing      "Scroll"}]])))