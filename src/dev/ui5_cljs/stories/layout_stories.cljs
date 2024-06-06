(ns ui5-cljs.stories.layout-stories
  (:require
   [reagent.core :as r]
   [ui5-cljs.stories.utils :as utils]
   [ui5-cljs.layout :refer [layout row column]]))


(def ^:export default
  (utils/->default
   {:title     "Layout"
    :component layout
    :argTypes  {:gutter        {:type        {:name "number" :required false}
                                :description "Specified the space between rows and columns."
                                :control     {:type "number"}}
                :as            {:type        {:name "string" :required false}
                                :description "Sets the components outer HTML tag."
                                :control     {:type "text"}}
                :style         {:type        {:name "map" :required false}
                                :description "Element style which will be appended to the most outer element of a component. Use this prop carefully, some css properties might break the component."
                                :control     {:type nil}}
                :class         {:type        {:name "string" :required false}
                                :description "CSS Class Name which will be appended to the most outer element of a component. Use this prop carefully, overwriting CSS rules might break the component."
                                :control     "text"}
                :row->align    {:type        {:name "string" :required false}
                                :description "Property of the `row` component.
                                              Defines the alignment of the row items"
                                :control     {:type    "select"
                                              :options ["start" "center" "end" "stretch"]}}
                :row->style    {:type        {:name "map" :required false}
                                :description "Property of the `row` component.
                                              Element style for a row component. Use this prop carefully, some css properties might break the component."
                                :control     {:type nil}}
                :column->span  {:type        {:name "number" :required false}
                                :description "Property of the `column` component.
                                              Defines the number of columns the component should span."
                                :control     {:type "number"}}
                :column->style {:type        {:name "map" :required false}
                                :description "Property of the `row` component.
                                              Element style for a column component. Use this prop carefully, some css properties might break the component."
                                :control     {:type nil}}}}))


(def col-style
  {:text-align "center"
   :border     "1px solid #2662fc"})

(def col-style-pad
  {:text-align "center"
   :border     "1px solid #2662fc"
   :padding    "10px"})


(defn ^:export layout-basic [args]
  (r/as-element
   [layout {:gutter 8}
    [row
     [column {:style col-style} "1"]
     [column {:style col-style} "2"]
     [column {:style col-style} "3"]
     [column {:style col-style} "4"]
     [column {:style col-style} "5"]
     [column {:style col-style} "6"]
     [column {:style col-style} "7"]
     [column {:style col-style} "8"]
     [column {:style col-style} "9"]
     [column {:style col-style} "10"]
     [column {:style col-style} "11"]
     [column {:style col-style} "12"]]
    [row
     [column {:span 6 :style col-style-pad}
      "span 6"]
     [column {:span 6 :style col-style-pad}
      "span 6"]]
    [row
     [column {:span 3 :style col-style-pad}
      "span 3"]
     [column {:span 3 :style col-style-pad}
      "span 3"]
     [column {:span 6 :style col-style-pad}
      "span 6"]]
    [row
     [column {:span 4 :style col-style-pad}
      "span 4"]
     [column {:span 8 :style col-style-pad}
      "span 8"]]
    [row
     [column {:span 2 :style col-style-pad}
      "span 2"]
     [column {:span 5 :style col-style-pad}
      "span 5"]
     [column {:span 5 :style col-style-pad}
      "span 5"]]]))


(defn ^:export layout-align-items [args]
  (r/as-element
   [layout {:gutter 8}
    [row
     [column {:span 4 :style col-style} "A short column can stretch to fill the space."]
     [column {:span 4 :style col-style} "A tall"
      [:br] "column defines"
      [:br] "the height"
      [:br] "of the row."]
     [column {:span 4 :style col-style} "Another"
      [:br] "column."]]
    [row {:align "start"}
     [column {:span 4 :style col-style} "A short column can align to the start (top) of the row."]
     [column {:span 4 :style col-style} "A tall"
      [:br] "column defines"
      [:br] "the height"
      [:br] "of the row."]
     [column {:span 4 :style col-style} "Another"
      [:br] "column."]]
    [row {:align "end"}
     [column {:span 4 :style col-style} "A short column can align to the end (bottom) of the row."]
     [column {:span 4 :style col-style} "A tall"
      [:br] "column defines"
      [:br] "the height"
      [:br] "of the row."]
     [column {:span 4 :style col-style} "Another"
      [:br] "column."]]
    [row {:align "center"}
     [column {:span 4 :style col-style} "A short column can align to the center (middle) of the row."]
     [column {:span 4 :style col-style} "A tall"
      [:br] "column defines"
      [:br] "the height"
      [:br] "of the row."]
     [column {:span 4 :style col-style} "Another"
      [:br] "column."]]]))
