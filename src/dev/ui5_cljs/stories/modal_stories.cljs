(ns ui5-cljs.stories.modal-stories
  (:require
   [reagent.core :as r]
   ["@ui5/webcomponents-react" :refer [Icon Bar]]
   [ui5-cljs.stories.utils :as utils]
   [ui5-cljs.button :refer [button]]
   [ui5-cljs.modal :refer [modal]]))


(def ^:export default
  (utils/->default
   {:title     "Modal"
    :component modal
    :argTypes  {:open                  {:type         {:name "boolean" :required true}
                                        :description  "Indicates if the element is open"
                                        :defaultValue false
                                        :control      "boolean"}
                :header-text           {:type        {:name "string" :required false}
                                        :description "Defines the header text.
                                                      Note: If header slot is provided, the headerText is ignored."
                                        :control     "text"}
                :draggable             {:type         {:name "boolean" :required false}
                                        :description  "Determines whether the component is draggable.
                                                       If this property is set to true, the Dialog will be draggable by its header.
                                                       Note: The component can be draggable only in desktop mode.
                                                       Note: This property overrides the default HTML \"draggable\" attribute native behavior.
                                                       When \"draggable\" is set to true, the native browser \"draggable\" behavior is prevented and only the Dialog custom logic (\"draggable by its header\") works."
                                        :defaultValue false
                                        :control      "boolean"}
                :resizable             {:type         {:name "boolean" :required false}
                                        :description  "Configures the component to be resizable.
                                                       If this property is set to true, the Dialog will have a resize handle in its bottom right corner in LTR languages.
                                                       In RTL languages, the resize handle will be placed in the bottom left corner.
                                                       Note: The component can be resizable only in desktop mode.
                                                       Note: Upon resizing, externally defined height and width styling will be ignored."
                                        :defaultValue false
                                        :control      "boolean"}
                :stretch               {:type         {:name "boolean" :required false}
                                        :description  "Determines whether the component should be stretched to fullscreen.
                                                       Note: The component will be stretched to approximately 90% of the viewport."
                                        :defaultValue false
                                        :control      "boolean"}
                :state                 {:type         {:name "string" :required false}
                                        :description  "Defines the state of the Dialog.
                                                       Note: If \"Error\" and \"Warning\" state is set, it will change the accessibility role to \"alertdialog\",
                                                       if the accessibleRole property is set to \"Dialog\"."
                                        :defaultValue "None"
                                        :control      "select"
                                        :options      ["Information" "Warning" "None" "Error" "Success"]}
                :prevent-focus-restore {:type         {:name "boolean" :required false}
                                        :description  "Defines if the focus should be returned to the previously focused element, when the popup closes."
                                        :defaultValue false
                                        :control      "boolean"}
                :initial-focus         {:type        {:name "string" :required false}
                                        :description "Defines the ID of the HTML Element, which will get the initial focus."
                                        :control     "text"}
                :header                {:type        {:name "component" :required false}
                                        :description "Defines the header HTML Element.
                                                      Note: When a ui5-bar is used in the header, you should remove the default dialog's paddings.
                                                      Note: If header slot is provided, the labelling of the dialog is a responsibility of the application developer. accessibleName should be used.
                                                      Note: The content of the prop will be rendered into a <slot> by assigning the respective slot attribute (slot=\"header\").
                                                      Since you can't change the DOM order of slots when declaring them within a prop,
                                                      it might prove beneficial to manually mount them as part of the component's children, especially when facing problems with the reading order of screen readers.
                                                      Note: When passing a custom React component to this prop, you have to make sure your component reads the slot prop and appends it to the most outer element of your component."
                                        :control     {:type nil}}
                :footer                {:type        {:name "component" :required false}
                                        :description "Defines the footer HTML Element.
                                                      Note: When a ui5-bar is used in the footer, you should remove the default dialog's paddings.
                                                      Note: The content of the prop will be rendered into a <slot> by assigning the respective slot attribute (slot=\"footer\").
                                                      Since you can't change the DOM order of slots when declaring them within a prop,
                                                      it might prove beneficial to manually mount them as part of the component's children, especially when facing problems with the reading order of screen readers.
                                                      Note: When passing a custom React component to this prop, you have to make sure your component reads the slot prop and appends it to the most outer element of your component. "
                                        :control     {:type nil}}
                :on-after-close        {:type        {:name "function" :required false}
                                        :description "Fired after the component is closed. This event does not bubble."
                                        :control     {:type nil}}
                :on-after-open         {:type        {:name "function" :required false}
                                        :description "Fired after the component is opened. This event does not bubble."
                                        :control     {:type nil}}
                :on-before-close       {:type        {:name "function" :required false}
                                        :description "Fired before the component is closed.
                                                      This event can be cancelled, which will prevent the popup from closing. This event does not bubble.
                                                      Note: Call event.preventDefault() inside the handler of this event to prevent its default action/s."
                                        :control     {:type nil}}
                :on-before-open        {:type        {:name "function" :required false}
                                        :description "Fired before the component is opened.
                                                      This event can be cancelled, which will prevent the popup from opening. This event does not bubble.
                                                      Note: Call event.preventDefault() inside the handler of this event to prevent its default action/s."
                                        :control     {:type nil}}
                :style                 {:type        {:name "map" :required false}
                                        :description "Element style which will be appended to the most outer element of a component. Use this prop carefully, some css properties might break the component."
                                        :control     {:type nil}}
                :class                 {:type        {:name "string" :required false}
                                        :description "CSS Class Name which will be appended to the most outer element of a component. Use this prop carefully, overwriting CSS rules might break the component."
                                        :control     "text"}
                :accessible-name       {:type        {:name "string" :required false}
                                        :description "Defines the accessible ARIA name of the component."
                                        :control     "text"}
                :accessible-name-ref   {:type        {:name "string" :required false}
                                        :description "Receives id(or many ids) of the elements that label the component."
                                        :control     "text"}
                :accessible-role       {:type         {:name "string" :required false}
                                        :description  "Describes the accessibility role of the button."
                                        :control      "select"
                                        :defaultValue "Button"
                                        :options      ["Button" "Link"]}}}))


(def model
  (r/atom false))


(defn ^:export modal-basic [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [:div
      [button {:on-click #(reset! model true)
               :label    "Open Modal"}]
      [modal (merge params
                    {:open           model
                     :on-after-close #(reset! model false)
                     :header-text    "Basic Modal"
                     :footer         [:> Bar {:design     "Footer"
                                              :endContent (r/as-element
                                                           [button {:label    "Close"
                                                                    :on-click #(reset! model false)}])}]})
       "Modal content"]])))
