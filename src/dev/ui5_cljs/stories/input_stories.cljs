(ns ui5-cljs.stories.input-stories
  (:require
   [reagent.core :as r]
   [ui5-cljs.stories.utils :as utils]
   [ui5-cljs.input :refer [input]]))


(def ^:export default
  (utils/->default
   {:title     "Input"
    :component input
    :argTypes  {:type                       {:type         {:name "string" :required false}
                                             :description  "Defines the HTML type of the component.
                                                            Notes:
                                                            The particular effect of this property differs depending on the browser and the current language settings, especially for type Number.
                                                            The property is mostly intended to be used with touch devices that use different soft keyboard layouts depending on the given input type."
                                             :control      "select"
                                             :defaultValue "Text"
                                             :options      ["Text" "Email" "Number" "Password" "Tel" "URL"]}
                :placeholder                {:type        {:name "string" :required false}
                                             :description "Defines a short hint intended to aid the user with data entry when the component has no value."
                                             :control     "text"}
                :icon                       {:type        {:name "node" :required false}
                                             :description "Defines the icon to be displayed in the component.
                                                           Note: The content of the prop will be rendered into a <slot> by assigning the respective slot attribute (slot=\"icon\").
                                                           Since you can't change the DOM order of slots when declaring them within a prop, it might prove beneficial to manually mount them as part of the component's children,
                                                           especially when facing problems with the reading order of screen readers."
                                             :control     {:type nil}}
                :max-length                 {:type        {:name "number" :required false}
                                             :description "Sets the maximum number of characters available in the input field.
                                                           Note: This property is not compatible with the ui5-input type InputType.Number.
                                                           If the ui5-input type is set to Number, the maxlength value is ignored."
                                             :control     "number"}
                :state                      {:type         {:name "string" :required false}
                                             :description  "Defines the value state of the component."
                                             :control      "select"
                                             :defaultValue "None"
                                             :options      ["Information" "Warning" "None" "Error" "Success"]}
                :message                    {:type        {:name "node" :required false}
                                             :description "Defines the value state message that will be displayed as pop up under the component.
                                                           Note: If not specified, a default text (in the respective language) will be displayed.
                                                           Note: The valueStateMessage would be displayed, when the component is in Information, Warning or Error value state.
                                                           Note: If the component has suggestionItems, the valueStateMessage would be displayed as part of the same popover, if used on desktop, or dialog - on phone.
                                                           Note: The content of the prop will be rendered into a <slot> by assigning the respective slot attribute (slot=\"valueStateMessage\").
                                                           Since you can't change the DOM order of slots when declaring them within a prop, it might prove beneficial to manually mount them as part of the component's children,
                                                           especially when facing problems with the reading order of screen readers."
                                             :control     {:type nil}}
                :name                       {:type        {:name "string" :required false}
                                             :description "Determines the name with which the component will be submitted in an HTML form.
                                                           Note: When set, a native input HTML element will be created inside the component so that it can be submitted as part of an HTML form.
                                                           Do not use this property unless you need to submit a form."
                                             :control     "text"}
                :value                      {:type        {:name "string" :required false}
                                             :description "Defines the value of the component."
                                             :control     "text"}
                :on-change                  {:type        {:name "function" :required false}
                                             :description "Fired when the input operation has finished by pressing Enter or on focusout."
                                             :control     {:type nil}}
                :on-input                   {:type        {:name "function" :required false}
                                             :description "Fired when the value of the component changes at each keystroke, and when a suggestion item has been selected."
                                             :control     {:type nil}}
                :on-suggestion-item-preview {:type        {:name "function" :required false}
                                             :description "Fired when the user navigates to a suggestion item via the ARROW keys, as a preview, before the final selection."
                                             :control     {:type nil}}
                :on-suggestion-item-select  {:type        {:name "function" :required false}
                                             :description "Fired when a suggestion item, that is displayed in the suggestion popup, is selected."
                                             :control     {:type nil}}
                :clear                      {:type         {:name "boolean" :required false}
                                             :description  "Defines whether the clear icon of the input will be shown."
                                             :defaultValue false
                                             :control      "boolean"}
                :suggestions                {:type         {:name "boolean" :required false}
                                             :description  "Defines whether the component should show suggestions, if such are present."
                                             :defaultValue false
                                             :control      "boolean"}
                :disabled                   {:type         {:name "boolean" :required false}
                                             :description  "Defines whether the component is in disabled state."
                                             :defaultValue false
                                             :control      "boolean"}
                :readonly                   {:type         {:name "boolean" :required false}
                                             :description  "Defines whether the component is read-only.
                                                            Note: A read-only component is not editable, but still provides visual feedback upon user interaction."
                                             :defaultValue false
                                             :control      "boolean"}
                :required                   {:type         {:name "boolean" :required false}
                                             :description  "Defines whether the component is required."
                                             :defaultValue false
                                             :control      "boolean"}
                :options                    {:type        {:name "collection" :required false}
                                             :description "Defines the suggestion items. Option should be a map of these keys:
                                                           id text type additional-text state description icon icon-end image.
                                                           id property is required
                                                           icon property should be a string (name of the icon).
                                                           But before you can that icon you should import it in your project.
                                                           e.g. (require [\"@ui5/webcomponents-icons/dist/<icon-name>\"])"
                                             :control     {:type nil}}
                :style                      {:type        {:name "map" :required false}
                                             :description "Element style which will be appended to the most outer element of a component. Use this prop carefully, some css properties might break the component."
                                             :control     {:type nil}}
                :class                      {:type        {:name "string" :required false}
                                             :description "CSS Class Name which will be appended to the most outer element of a component. Use this prop carefully, overwriting CSS rules might break the component."
                                             :control     "text"}
                :accessible-name            {:type        {:name "string" :required false}
                                             :description "Defines the accessible ARIA name of the component."
                                             :control     "text"}
                :accessible-name-ref        {:type        {:name "string" :required false}
                                             :description "Receives id(or many ids) of the elements that label the component."
                                             :control     "text"}}}))


(def model (r/atom "qwe"))


(defn ^:export input-basic [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [input (merge {:value     model
                    :on-change (fn [text]
                                 (reset! model text))}
                   params)])))


(defn model-value []
  [:div "Input value: " @model])


(defn ^:export input-with-suggest [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [:div
      [input (merge {:value                      model
                     :suggestions                true
                     :on-change                  (fn [text]
                                                   (reset! model text))
                     :on-suggestion-item-preview (fn [option]
                                                   (println "Option in focus" option))
                     :on-suggestion-item-select  (fn [option]
                                                   (reset! model (:text option)))
                     :options                    [{:id 1 :text "Option 1"}
                                                  {:id 2 :text "Option 2"}
                                                  {:id 3 :text "Option 3"}]}
                    params)]
      [model-value]])))
