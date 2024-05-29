(ns ui5-cljs.stories.select-stories
  (:require
   [reagent.core :as r]
   [ui5-cljs.stories.utils :as utils]
   [ui5-cljs.select :refer [single-select]]))


(def ^:export default
  (utils/->default
   {:title     "Select"
    :component single-select
    :argTypes  {:state               {:type         {:name "string" :required false}
                                      :description  "Defines the value state of the component."
                                      :control      "select"
                                      :defaultValue "None"
                                      :options      ["Information" "Warning" "None" "Error" "Success"]}
                :message             {:type        {:name "node" :required false}
                                      :description "Defines the value state message that will be displayed as pop up under the component.
                                                    Note: If not specified, a default text (in the respective language) will be displayed.
                                                    Note: The valueStateMessage would be displayed, when the component is in Information, Warning or Error value state.
                                                    Note: If the component has suggestionItems, the valueStateMessage would be displayed as part of the same popover, if used on desktop, or dialog - on phone.
                                                    Note: The content of the prop will be rendered into a <slot> by assigning the respective slot attribute (slot=\"valueStateMessage\").
                                                    Since you can't change the DOM order of slots when declaring them within a prop, it might prove beneficial to manually mount them as part of the component's children,
                                                    especially when facing problems with the reading order of screen readers."
                                      :control     {:type nil}}
                :label               {:type        {:name "node" :required false}
                                      :description "Defines the HTML element that will be displayed in the component input part, representing the selected option.
                                                    Note: If not specified and SelectMenuOption is used, either the option's display-text or its textContent will be displayed.
                                                    Note: If not specified and Option is used, the option's textContent will be displayed.
                                                    Note: The content of the prop will be rendered into a <slot> by assigning the respective slot attribute (slot=\"label\").
                                                    Since you can't change the DOM order of slots when declaring them within a prop, it might prove beneficial to manually mount them as part of the component's children,
                                                    especially when facing problems with the reading order of screen readers."
                                      :control     "text"}
                :name                {:type        {:name "string" :required false}
                                      :description "Determines the name with which the component will be submitted in an HTML form. The value of the component will be the value of the currently selected Option.
                                                    Note: When set, a native input HTML element will be created inside the Select so that it can be submitted as part of an HTML form.
                                                    Do not use this property unless you need to submit a form."
                                      :control     "text"}
                :menu                {:type        {:name "string" :required false}
                                      :description "Defines a reference (ID or DOM element) of component's menu of options as alternative to define the select's dropdown.
                                                    Note: Usage of SelectMenu is recommended."
                                      :control     "text"}
                :value               {:type        {:name "string" :required false}
                                      :description "Defines the value of the component:
                                                    when get - returns the value of the component, e.g. the value property of the selected option or its text content.
                                                    when set - selects the option with matching value property or text content.
                                                    Note: If the given value does not match any existing option, the first option will get selected."
                                      :control     "text"}
                :on-change           {:type        {:name "function" :required false}
                                      :description "Fired when the selected option changes.
                                                    Note: Call event.preventDefault() inside the handler of this event to prevent its default action/s."
                                      :control     {:type nil}}
                :on-live-change      {:type        {:name "function" :required false}
                                      :description "Fired when the user navigates through the options, but the selection is not finalized, or when pressing the ESC key to revert the current selection."
                                      :control     {:type nil}}
                :on-open             {:type        {:name "function" :required false}
                                      :description "Fired after the component's dropdown menu opens."
                                      :control     {:type nil}}
                :on-close            {:type        {:name "function" :required false}
                                      :description "Fired after the component's dropdown menu closes."
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
                :options             {:type        {:name "collection" :required false}
                                      :description "An array with options to be selected. Option should be a map of these keys:
                                                     id, text, icon, disabled, additional-text.
                                                     Icon property should be a string (name of the icon).
                                                     But before you can that icon you should import it in your project.
                                                     e.g. (require [\"@ui5/webcomponents-icons/dist/<icon-name>\"])"
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


(def model (r/atom nil))

(defn ^:export single-select-basic [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [single-select (merge {:label     "Select"
                            :value     model
                            :options   [{:id 1 :text "One"}
                                        {:id 2 :text "Two"}
                                        {:id 3 :text "Three"}]
                            :on-change (fn [selected]
                                         (reset! model selected))}
                           params)])))


(defn ^:export select-states-basic [args]
  (r/as-element
   [:div
    [:div
     [single-select {:label   "Select"
                     :state   "Information"
                     :message [:span "Information message"]
                     :options [{:id 1 :text "One"} {:id 2 :text "Two"} {:id 3 :text "Three"}]}]
     [single-select {:label   "Select"
                     :state   "Warning"
                     :message [:span "Warning message"]
                     :options [{:id 1 :text "One"} {:id 2 :text "Two"} {:id 3 :text "Three"}]}]
     [single-select {:label   "Select"
                     :state   "Error"
                     :message [:span "Error message"]
                     :options [{:id 1 :text "One"} {:id 2 :text "Two"} {:id 3 :text "Three"}]}]
     [single-select {:label   "Select"
                     :state   "Success"
                     :message [:span "Success message"]
                     :options [{:id 1 :text "One"} {:id 2 :text "Two"} {:id 3 :text "Three"}]}]]

    [:div
     [single-select {:label    "Select"
                     :disabled true
                     :options  [{:id 1 :text "One"} {:id 2 :text "Two"} {:id 3 :text "Three"}]}]
     [single-select {:label    "Select"
                     :readonly true
                     :options  [{:id 1 :text "One"} {:id 2 :text "Two"} {:id 3 :text "Three"}]}]
     [single-select {:label    "Select"
                     :required true
                     :options  [{:id 1 :text "One"} {:id 2 :text "Two"} {:id 3 :text "Three"}]}]]]))
