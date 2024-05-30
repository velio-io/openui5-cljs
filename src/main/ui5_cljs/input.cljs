(ns ui5-cljs.input
  (:require
   ["@ui5/webcomponents-react" :refer [Input Icon SuggestionItem]]
   ["@ui5/webcomponents/dist/features/InputSuggestions"]
   [applied-science.js-interop :as j]
   [ui5-cljs.utils :as utils]))


(defn input
  [{:keys [type value name placeholder state icon message max-length options
           on-change on-input on-suggestion-item-preview on-suggestion-item-select disabled readonly required
           clear suggestions style class accessible-name accessible-name-ref]
    :or   {type "Text"}}]
  (into
    [:> Input (utils/assoc-some
                {:type                    type
                 :onChange                (fn [event]
                                            (when (fn? on-change)
                                              (on-change (j/get-in event [:target :value]))))
                 :onInput                 (fn [event]
                                            (when (fn? on-input)
                                              (on-input (j/get-in event [:target :value]))))
                 :onSuggestionItemPreview (fn [event]
                                            (when (fn? on-suggestion-item-preview)
                                              (let [options (map #(update % :id str) options)
                                                    id      (j/get-in event [:detail :item :dataset :id])
                                                    option  (utils/find-by :id id options)]
                                                (on-suggestion-item-preview option))))
                 :onSuggestionItemSelect  (fn [event]
                                            (when (fn? on-suggestion-item-select)
                                              (let [options (map #(update % :id str) options)
                                                    id      (j/get-in event [:detail :item :dataset :id])
                                                    option  (utils/find-by :id id options)]
                                                (on-suggestion-item-select option))))}
                :value (utils/model->value value)
                :name name
                :placeholder placeholder
                :valueState state
                :valueStateMessage (utils/value->element message)
                :maxlength max-length
                :disabled (utils/model->value disabled)
                :readonly readonly
                :required required
                :showClearIcon clear
                :showSuggestions suggestions
                :style style
                :className class
                :accessibleName accessible-name
                :accessibleNameRef accessible-name-ref
                :icon (when (some? icon)
                        (utils/value->element [:> Icon {:name icon}])))]
    (map (fn [{:keys [id text type additional-text state description icon icon-end image]}]
           [:> SuggestionItem (utils/assoc-some {:text text}
                                :data-id id
                                :type type
                                :additionalText additional-text
                                :additionalTextState state
                                :description description
                                :icon icon
                                :iconEnd icon-end
                                :image image)]))
    options))
