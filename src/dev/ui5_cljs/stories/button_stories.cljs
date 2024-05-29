(ns ui5-cljs.stories.button-stories
  (:require
   [reagent.core :as r]
   [ui5-cljs.stories.utils :as utils]
   [ui5-cljs.button :refer [button]]))


(def ^:export default
  (utils/->default
   {:title     "Button"
    :component button
    :argTypes  {:label               {:type        {:name "string" :required false}
                                      :description "Applies the text that displays on the button"
                                      :control     "text"}
                :design              {:type         {:name "string" :required false}
                                      :description  "Defines the component design."
                                      :control      "select"
                                      :defaultValue "Default"
                                      :options      ["Positive" "Negative" "Default" "Transparent" "Emphasized" "Attention"]}
                :type                {:type        {:name "string" :required false}
                                      :description "Defines whether the button has special form-related functionality."
                                      :control     "select"
                                      :options     ["Button" "Submit" "Reset"]}
                :disabled            {:type         {:name "boolean" :required false}
                                      :description  "Defines whether the component is disabled. A disabled component can't be pressed or focused, and it is not in the tab chain."
                                      :defaultValue false
                                      :control      "boolean"}
                :icon                {:type        {:name "string" :required false}
                                      :description "Defines the icon, displayed as graphical element within the component.
                                                    The SAP-icons font provides numerous options.
                                                    See all the available icons within the Icon Explorer https://sdk.openui5.org/test-resources/sap/m/demokit/iconExplorer/webapp/index.html"
                                      :control     "text"}
                :icon-end            {:type         {:name "boolean" :required false}
                                      :description  "Defines whether the icon should be displayed after the component text.\n"
                                      :defaultValue false
                                      :control      "boolean"}
                :tooltip             {:type        {:name "string" :required false}
                                      :description "Defines the tooltip of the component."
                                      :control     "text"}
                :on-click            {:type        {:name "function" :required false}
                                      :description "Fired when the component is activated either with a mouse/tap or by using the Enter or Space key."
                                      :control     {:type nil}}
                :style               {:type        {:name "map" :required false}
                                      :description "Element style which will be appended to the most outer element of a component. Use this prop carefully, some css properties might break the component."
                                      :control     {:type nil}}
                :class               {:type        {:name "string" :required false}
                                      :description "CSS Class Name which will be appended to the most outer element of a component. Use this prop carefully, overwriting CSS rules might break the component."
                                      :control     "text"}
                :accessible-name     {:type        {:name "string" :required false}
                                      :description "Defines the accessible ARIA name of the component."
                                      :control     "text"}
                :accessible-name-ref {:type        {:name "string" :required false}
                                      :description "Receives id(or many ids) of the elements that label the component."
                                      :control     "text"}
                :accessible-role     {:type         {:name "string" :required false}
                                      :description  "Describes the accessibility role of the button."
                                      :control      "select"
                                      :defaultValue "Button"
                                      :options      ["Button" "Link"]}}}))


(defn ^:export button-basic [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [button (merge {:label "I'm Button"} params)])))


(defn ^:export button-styled [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [button (merge {:label "I'm Button"
                     :style {:color "purple" :font-size "30px"}}
                    params)])))
