(ns ui5-cljs.select
  (:require
   [applied-science.js-interop :as j]
   [ui5-cljs.utils :as utils]
   ["@ui5/webcomponents-react" :refer [Select Option MultiComboBox MultiComboBoxItem Icon]]))


(defn single-select
  [{:keys [state message label name menu value on-change on-live-change on-open on-close
           disabled readonly required style class accessible-name accessible-name-ref options]}]
  (into
    [:> Select (utils/assoc-some {}
                 :valueState (utils/model->value state)
                 :valueStateMessage (utils/value->element message)
                 :label (utils/value->element label)
                 :name name
                 :menu menu
                 :value (utils/model->value value)
                 :onChange (fn [event]
                             (when (fn? on-change)
                               (on-change (j/get-in event [:detail :selectedOption :value]))))
                 :onLiveChange on-live-change
                 :onOpen on-open
                 :onClose on-close
                 :disabled (utils/model->value disabled)
                 :readonly readonly
                 :required required
                 :style style
                 :className class
                 :accessibleName accessible-name
                 :accessibleNameRef accessible-name-ref)]
    (map (fn [{:keys [id text icon disabled additional-text]}]
           [:> Option (utils/assoc-some {:key id :value id}
                        :icon icon
                        :disabled disabled
                        :additionalText additional-text)
            text]))
    options))


(defn multi-select
  [{:keys [state message icon placeholder value on-change on-input on-open-change on-selection-change selected
           clear select-all disabled readonly required style class accessible-name accessible-name-ref options]}]
  (let [options  (mapv #(update % :id str) options)
        selected (set (map str selected))]
    (into
      [:> MultiComboBox (utils/assoc-some
                          {:onChange          (fn [event]
                                                (when (fn? on-change)
                                                  (on-change (j/get-in event [:target :value]))))
                           :onInput           (fn [event]
                                                (when (fn? on-input)
                                                  (on-input (j/get-in event [:target :value]))))
                           :onSelectionChange (fn [event]
                                                (when (fn? on-selection-change)
                                                  (->> (j/get-in event [:detail :items])
                                                       (mapv (fn [item]
                                                               (let [v (j/get-in item [:dataset :id])]
                                                                 (utils/find-by :id v options))))
                                                       (on-selection-change))))}
                          :valueState (utils/model->value state)
                          :valueStateMessage (utils/value->element message)
                          :icon (when (some? icon)
                                  (utils/value->element [:> Icon {:name icon}]))
                          :placeholder placeholder
                          :value (utils/model->value value)
                          :onOpenChange on-open-change
                          :showClearIcon clear
                          :showSelectAll select-all
                          :disabled (utils/model->value disabled)
                          :readonly readonly
                          :required required
                          :allowCustomValues true
                          :style style
                          :className class
                          :accessibleName accessible-name
                          :accessibleNameRef accessible-name-ref)]
      (map (fn [{:keys [id text additional-text]}]
             [:> MultiComboBoxItem (utils/assoc-some
                                     {:key      id
                                      :data-id  id
                                      :selected (contains? selected id)
                                      :text     text}
                                     :additionalText additional-text)]))
      options)))