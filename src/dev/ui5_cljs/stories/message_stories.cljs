(ns ui5-cljs.stories.message-stories
  (:require
   [reagent.core :as r]
   ["@ui5/webcomponents-react" :refer [Icon]]
   [ui5-cljs.stories.utils :as utils]
   [ui5-cljs.message :refer [message]]))


(def ^:export default
  (utils/->default
   {:title     "Message"
    :component message
    :argTypes  {:design            {:type        {:name "string" :required false}
                                    :description "Defines the visual indication and behavior of the message."
                                    :control     {:type    "select"
                                                  :options ["Information" "Positive" "Negative" "Warning"]}}
                :icon              {:type        {:name "component" :required false}
                                    :description "Defines the icon to be displayed as graphical element within the message.
                                                  Note: If no icon is given, the default icon for the component type will be used.
                                                  The SAP-icons font provides numerous options.
                                                  See all the available icons in the Icon Explorer (https://sdk.openui5.org/test-resources/sap/m/demokit/iconExplorer/webapp/index.html)."
                                    :control     {:type nil}}
                :hide-icon         {:type        {:name "boolean" :required false}
                                    :description "Defines whether the MessageStrip will show an icon in the beginning.
                                                  You can directly provide an icon with the icon slot.
                                                  Otherwise, the default icon for the type will be used."
                                    :control     "boolean"}
                :hide-close-button {:type        {:name "boolean" :required false}
                                    :description "Determines if the close button should be hidden."
                                    :control     "boolean"}
                :on-close          {:type        {:name "function" :required false}
                                    :description "Fired when the close button is pressed either with a click/tap or by using the Enter or Space key."
                                    :control     {:type "function"}}
                :style             {:type        {:name "map" :required false}
                                    :description "Element style which will be appended to the most outer element of a component. Use this prop carefully, some css properties might break the component."
                                    :control     {:type nil}}
                :class-name        {:type        {:name "string" :required false}
                                    :description "CSS Class Name which will be appended to the most outer element of a component. Use this prop carefully, overwriting CSS rules might break the component."
                                    :control     "text"}}}))


(defn ^:export message-basic [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [message (merge {:design   "Information"
                      :icon     [:> Icon {:name "employee"}]
                      :on-close (fn [event]
                                  (js/console.log "on-close"))}
                     params)
      "Basic message"])))


(defn ^:export message-variants [args]
  (r/as-element
   [:div
    [:div {:style {:margin "8px"}}
     [message {:design "Information"} "Information message"]]
    [:div {:style {:margin "8px"}}
     [message {:design "Positive"} "Positive message"]]
    [:div {:style {:margin "8px"}}
     [message {:design "Negative"} "Negative message"]]
    [:div {:style {:margin "8px"}}
     [message {:design "Warning"} "Warning message"]]]))
