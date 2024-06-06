(ns ui5-cljs.stories.label-stories
  (:require
   [reagent.core :as r]
   [ui5-cljs.stories.utils :as utils]
   [ui5-cljs.label :refer [label]]))


(def ^:export default
  (utils/->default
   {:title     "Label"
    :component label
    :argTypes  {:for           {:type        {:name "string" :required false}
                                :description "Defines the labeled input by providing its ID.
                                              Note: Can be used with both Input and native input."
                                :control     "text"}
                :required      {:type         {:name "boolean" :required false}
                                :description  "Defines whether an asterisk character is added to the component text.
                                               Note: Usually indicates that user input (bound with the for property) is required.
                                               In that case the required property of the corresponding input should also be set."
                                :defaultValue false
                                :control      "boolean"}
                :colon         {:type         {:name "boolean" :required false}
                                :description  "Defines whether colon is added to the component text.
                                               Note: Usually used in forms."
                                :defaultValue false
                                :control      "boolean"}
                :wrapping-type {:type         {:name "string" :required false}
                                :description  "Defines how the text of a component will be displayed when there is not enough space.
                                               Note: for option \"Normal\" the text will wrap and the words will not be broken based on hyphenation."
                                :control      "select"
                                :defaultValue "None"
                                :options      ["None" "Normal"]}
                :style         {:type        {:name "map" :required false}
                                :description "Element style which will be appended to the most outer element of a component. Use this prop carefully, some css properties might break the component."
                                :control     {:type nil}}
                :class         {:type        {:name "string" :required false}
                                :description "CSS Class Name which will be appended to the most outer element of a component. Use this prop carefully, overwriting CSS rules might break the component."
                                :control     "text"}}}))


(defn ^:export label-basic [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [label params
      "Label Text"])))
