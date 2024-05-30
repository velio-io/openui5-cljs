(ns ui5-cljs.stories.checkbox-stories
  (:require
   [reagent.core :as r]
   [ui5-cljs.stories.utils :as utils]
   [ui5-cljs.checkbox :refer [checkbox]]))


(def ^:export default
  (utils/->default
   {:title     "Checkbox"
    :component checkbox
    :argTypes  {:text                {:type        {:name "string" :required false}
                                      :description "Defines the text of the component."
                                      :control     "text"}
                :state               {:type         {:name "string" :required false}
                                      :description  "Defines the value state of the component."
                                      :control      "select"
                                      :defaultValue "None"
                                      :options      ["Information" "Warning" "None" "Error" "Success"]}
                :on-change           {:type        {:name "function" :required false}
                                      :description "Fired when the component checked state changes."
                                      :control     {:type nil}}
                :checked             {:type         {:name "boolean" :required false}
                                      :description  "Defines if the component is checked. Can be a reagent atom.
                                                     Note: The property can be changed with user interaction, either by cliking/tapping on the component, or by pressing the Enter or Space key."
                                      :defaultValue false
                                      :control      "boolean"}
                :disabled            {:type         {:name "boolean" :required false}
                                      :description  "Defines whether the component is disabled.
                                                     Note: A disabled component is completely noninteractive."
                                      :defaultValue false
                                      :control      "boolean"}
                :display-only        {:type         {:name "boolean" :required false}
                                      :description  "Determines whether the CheckBox is in display only state.
                                                     When set to true, the CheckBox is not interactive, not editable, not focusable and not in the tab chain. This setting is used for forms in review mode.
                                                     Note: When the property disabled is set to true this property has no effect."
                                      :defaultValue false
                                      :control      "boolean"}
                :indeterminate       {:type         {:name "boolean" :required false}
                                      :description  "Defines whether the component is displayed as partially checked.
                                                     Note: The indeterminate state can be set only programmatically and can’t be achieved by user interaction and the resulting visual state depends on the values of the indeterminate and checked properties:
                                                     If the component is checked and indeterminate, it will be displayed as partially checked
                                                     If the component is checked and it is not indeterminate, it will be displayed as checked
                                                     If the component is not checked, it will be displayed as not checked regardless value of the indeterminate attribute"
                                      :defaultValue false
                                      :control      "boolean"}
                :readonly            {:type         {:name "boolean" :required false}
                                      :description  "Defines whether the component is read-only.
                                                     Note: A read-only component is not editable, but still provides visual feedback upon user interaction."
                                      :defaultValue false
                                      :control      "boolean"}
                :required            {:type         {:name "boolean" :required false}
                                      :description  "Defines whether the component is required."
                                      :defaultValue false
                                      :control      "boolean"}
                :wrapping            {:type         {:name "string" :required false}
                                      :description  "Defines whether the component text wraps when there is not enough space.
                                                     Note: for option \"Normal\" the text will wrap and the words will not be broken based on hyphenation."
                                      :control      "select"
                                      :defaultValue "None"
                                      :options      ["None" "Normal"]}
                :name                {:type        {:name "string" :required false}
                                      :description "Determines the name with which the component will be submitted in an HTML form.
                                                    Important: For the name property to have effect, you must add the following import to your project: import \"@ui5/webcomponents/dist/features/InputElementsFormSupport.js\";
                                                    Note: When set, a native input HTML element will be created inside the component so that it can be submitted as part of an HTML form. Do not use this property unless you need to submit a form."
                                      :control     "text"}
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
                                      :control     "text"}}}))


(def model (r/atom false))

(defn ^:export checkbox-basic [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [checkbox (merge {:text      "CheckBox"
                       :checked   model
                       :on-change (fn [checked]
                                    (reset! model checked))}
                      params)])))


(defn ^:export checkbox-states [args]
  (r/as-element
   [:<>
    [checkbox {:text "Error" :state "Error"}]
    [checkbox {:text "Warning" :state "Warning"}]
    [checkbox {:checked "true" :disabled "true" :text "Disabled"}]
    [checkbox {:checked "true" :readonly "true" :text "Readonly"}]
    [checkbox {:checked "true" :disabled "true" :text "Error disabled" :state "Error"}]
    [checkbox {:checked "true" :disabled "true" :text "Warning disabled " :state "Warning"}]
    [checkbox {:checked "true" :readonly "true" :text "Error readonly" :state "Error"}]
    [checkbox {:checked "true" :readonly "true" :text "Warning readonly" :state "Warning"}]]))
