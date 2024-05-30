(ns ui5-cljs.stories.multi-select-stories
  (:require
   [reagent.core :as r]
   [ui5-cljs.stories.utils :as utils]
   [ui5-cljs.select :refer [multi-select]]))


(def ^:export default
  (utils/->default
   {:title     "Multi Select"
    :component multi-select
    :argTypes  {:placeholder         {:type        {:name "string" :required false}
                                      :description "Defines a short hint intended to aid the user with data entry when the component has no value."
                                      :control     "text"}
                :icon                {:type        {:name "node" :required false}
                                      :description "Defines the icon to be displayed in the component.
                                                    Note: The content of the prop will be rendered into a <slot> by assigning the respective slot attribute (slot=\"icon\").
                                                    Since you can't change the DOM order of slots when declaring them within a prop, it might prove beneficial to manually mount them as part of the component's children,
                                                    especially when facing problems with the reading order of screen readers."
                                      :control     {:type nil}}
                :state               {:type         {:name "string" :required false}
                                      :description  "Defines the value state of the component."
                                      :control      "select"
                                      :defaultValue "None"
                                      :options      ["Information" "Warning" "None" "Error" "Success"]}
                :message             {:type        {:name "node" :required false}
                                      :description "Defines the value state message that will be displayed as pop up under the component. The value state message slot should contain only one root element.
                                                    Note: If not specified, a default text (in the respective language) will be displayed.
                                                    Note: The valueStateMessage would be displayed, when the component is in Information, Warning or Error value state.
                                                    Note: The content of the prop will be rendered into a <slot> by assigning the respective slot attribute (slot=\"valueStateMessage\"). Since you can't change the DOM order of slots when declaring them within a prop, it might prove beneficial to manually mount them as part of the component's children, especially when facing problems with the reading order of screen readers."
                                      :control     {:type nil}}
                :value               {:type        {:name "string" :required false}
                                      :description "Defines the value of the component:
                                                    when get - returns the value of the component, e.g. the value property of the selected option or its text content.
                                                    when set - selects the option with matching value property or text content.
                                                    Note: If the given value does not match any existing option, the first option will get selected."
                                      :control     "text"}
                :on-change           {:type        {:name "function" :required false}
                                      :description "Fired when the input operation has finished by pressing Enter or on focusout."
                                      :control     {:type nil}}
                :on-input            {:type        {:name "function" :required false}
                                      :description "Fired when the value of the component changes at each keystroke or clear icon is pressed."
                                      :control     {:type nil}}
                :on-open-change      {:type        {:name "function" :required false}
                                      :description "Fired when the dropdown is opened or closed."
                                      :control     {:type nil}}
                :on-selection-change {:type        {:name "function" :required false}
                                      :description "Fired when selection is changed by user interaction."
                                      :control     {:type nil}}
                :disabled            {:type         {:name "boolean" :required false}
                                      :description  "Defines whether the component is in disabled state."
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
                :clear               {:type         {:name "boolean" :required false}
                                      :description  "Defines whether the clear icon of the multi-combobox will be shown."
                                      :defaultValue false
                                      :control      "boolean"}
                :select-all          {:type         {:name "boolean" :required false}
                                      :description  "Determines if the select all checkbox is visible on top of suggestions."
                                      :defaultValue false
                                      :control      "boolean"}
                :options             {:type        {:name "collection" :required false}
                                      :description "An array with options to be selected. Option should be a map of these keys:
                                                    id text additional-text.
                                                    id property is required."
                                      :control     {:type nil}}
                :selected            {:type        {:name "collection" :required false}
                                      :description "An array of options ids which should be selected initially."
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
                                      :control     "text"}}}))


(defn ^:export multi-select-basic [args]
  (r/as-element
   [multi-select {:options             [{:id 1 :text "One"}
                                        {:id 2 :text "Two"}
                                        {:id 3 :text "Three"}]
                  :selected            #{2}
                  :placeholder         "Select items"
                  :on-change           (fn [item]
                                         (println item))
                  :on-input            (fn [item]
                                         (println item))
                  :on-selection-change (fn [items]
                                         (println items))}]))