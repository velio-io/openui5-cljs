(ns ui5-cljs.stories.card-stories
  (:require
   [reagent.core :as r]
   ["@ui5/webcomponents-react" :refer [Icon ThemeProvider]]
   ["@ui5/webcomponents-icons/dist/person-placeholder"]
   [ui5-cljs.stories.utils :as utils]
   [ui5-cljs.card :refer [card numeric-side-indicator]]))


(def ^:export default
  (utils/->default
   {:title     "Card"
    :component card
    :argTypes  {:header->title       {:type        {:name "string" :required false}
                                      :description "Defines the title text."
                                      :control     "text"}
                :header->subtitle    {:type        {:name "string" :required false}
                                      :description "Defines the subtitle text."
                                      :control     "text"}
                :header->status      {:type        {:name "string" :required false}
                                      :description "Defines the status text."
                                      :control     "text"}
                :header->avatar      {:type        {:name "node" :required false}
                                      :description "Defines an avatar image, displayed in the left most part of the header.
                                                    The content of the prop will be rendered into a <slot> by assigning the respective slot attribute (slot=\"avatar\").
                                                    Note: When passing a custom React component to this prop, you have to make sure your component reads the slot prop and appends it to the most outer element of your component.
                                                    Note: Only for the regular card header."
                                      :control     "text"}
                :header->action      {:type        {:name "string" :required false}
                                      :description "Defines an action, displayed in the right most part of the header.
                                                    The content of the prop will be rendered into a <slot> by assigning the respective slot attribute (slot=\"action\").
                                                    Note: When passing a custom React component to this prop, you have to make sure your component reads the slot prop and appends it to the most outer element of your component.
                                                    Note: Only for the regular card header."
                                      :control     "text"}
                :header->style       {:type        {:name "map" :required false}
                                      :description "Element style which will be appended to the most outer element of a component. Use this prop carefully, some css properties might break the component."
                                      :control     {:type nil}}
                :header->class       {:type        {:name "string" :required false}
                                      :description "CSS Class Name which will be appended to the most outer element of a component. Use this prop carefully, overwriting CSS rules might break the component."
                                      :control     "text"}
                :header->interactive {:type         {:name "boolean" :required false}
                                      :description  "Defines if the component would be interactive, e.g gets hover effect, gets focus outline and click event is fired, when pressed."
                                      :defaultValue false
                                      :control      "boolean"}
                :header->on-click    {:type        {:name "function" :required false}
                                      :description "Fired when the component is activated by mouse/tap or by using the Enter or Space key.
                                                    Note: The event would be fired only if the interactive property is set to true."
                                      :control     {:type nil}}
                :header->analytical  {:type         {:name "boolean" :required false}
                                      :description  "Defines if the component would use the analytical card header."
                                      :defaultValue false
                                      :control      "boolean"}
                :header->value       {:type        {:name "string" :required false}
                                      :description "The numeric value of the main number indicator.
                                                    Note: Only for the analytical card header."
                                      :control     "text"}
                :header->scale       {:type        {:name "string" :required false}
                                      :description "Defines the unit of measurement (scaling prefix) for the main indicator. Financial characters can be used for currencies and counters. The International System of Units (SI) prefixes can be used.
                                                    Note: Only for the analytical card header."
                                      :control     "text"}
                :header->description {:type        {:name "string" :required false}
                                      :description "Additional text which adds more details to what is shown in the numeric header.
                                                    Note: Only for the analytical card header."
                                      :control     "text"}
                :header->unit        {:type        {:name "string" :required false}
                                      :description "General unit of measurement for the header. Displayed as side information to the subtitle.
                                                    Note: Only for the analytical card header."
                                      :control     "text"}
                :header->trend       {:type         {:name "string" :required false}
                                      :description  "The direction of the trend arrow. Shows deviation for the value of the main number indicator.
                                                     Note: Only for the analytical card header."
                                      :control      "select"
                                      :defaultValue "None"
                                      :options      ["Down" "Up" "None"]}
                :header->state       {:type         {:name "string" :required false}
                                      :description  "The semantic color which represents the state of the main number indicator.
                                                     Note: Only for the analytical card header."
                                      :control      "select"
                                      :defaultValue "None"
                                      :options      ["Critical" "Error" "Good" "Neutral" "None"]}
                :header->children    {:type        {:name "node" :required false}
                                      :description "Additional side number indicators. For example \"Deviation\" and \"Target\". Not more than two side indicators should be used.
                                                    Note: Only for the analytical card header.
                                                    Note: Although this prop accepts all HTML Elements, it is strongly recommended that you only use NumericSideIndicator in order to preserve the intended design."
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


(defn ^:export card-basic [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [card (merge {:header {:title    "TeamSpace"
                            :subtitle "Direct Reports"
                            :status   "3 of 5"
                            :avatar   [:> Icon {:name "person-placeholder"}]}
                   :style  {:width "300px"}}
                  params)
      [:div {:style {:margin "30px"}}
       "Content goes here"]])))


(defn ^:export card-analytical-header [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [:> ThemeProvider
      [card (merge {:header {:analytical  true
                             :description "Q1, 2018"
                             :scale       "K"
                             :state       "Error"
                             :subtitle    "Revenue"
                             :title       "Project Cloud Transformation"
                             :trend       "Down"
                             :unit        "EUR"
                             :value       "65.34"
                             :children    [[numeric-side-indicator {:number "100" :title "Target" :unit "k"}]
                                           [numeric-side-indicator {:number "34.7" :state "Critical" :title "Deviation" :unit "%"}]]}}
                   params)]])))
