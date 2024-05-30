(ns ui5-cljs.button
  (:require
   ["@ui5/webcomponents/dist/features/InputElementsFormSupport.js"]
   ["@ui5/webcomponents-react" :refer [Button]]
   [ui5-cljs.utils :as utils]))


(defn button
  [{:keys [label design type disabled icon icon-end tooltip on-click style class
           accessible-name accessible-name-ref accessible-role]}]
  [:> Button (utils/assoc-some {}
               :design design
               :type type
               :disabled (utils/model->value disabled)
               :icon icon
               :iconEnd icon-end
               :tooltip tooltip
               :onClick on-click
               :style style
               :className class
               :accessibleName accessible-name
               :accessibleNameRef accessible-name-ref
               :accessibleRole accessible-role)
   label])
