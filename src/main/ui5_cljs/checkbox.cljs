(ns ui5-cljs.checkbox
  (:require
   ["@ui5/webcomponents-react" :refer [CheckBox]]
   [ui5-cljs.utils :as utils]))


(defn checkbox
  [{:keys [text state on-change checked disabled display-only indeterminate
           readonly required wrapping name style class accessible-name accessible-name-ref]}]
  (let [checked (utils/model->value checked)]
    [:> CheckBox (utils/assoc-some {}
                   :text text
                   :onChange (fn [_event]
                               (when (fn? on-change)
                                 (on-change (not checked))))
                   :checked checked
                   :valueState state
                   :disabled (utils/model->value disabled)
                   :displayOnly display-only
                   :indeterminate indeterminate
                   :readonly readonly
                   :required required
                   :wrappingType wrapping
                   :name name
                   :style style
                   :className class
                   :accessibleName accessible-name
                   :accessibleNameRef accessible-name-ref)]))
