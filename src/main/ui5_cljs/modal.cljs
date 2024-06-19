(ns ui5-cljs.modal
  (:require
   [reagent.core :as r]
   [ui5-cljs.utils :as utils]
   ["@ui5/webcomponents-react" :refer [Dialog]]))


(defn modal
  [{:keys [open header-text draggable resizable stretch state prevent-focus-restore initial-focus
           header footer on-after-close on-after-open on-before-close on-before-open style class
           accessible-name accessible-name-ref accessible-role]}]
  (let [component (r/current-component)
        children  (r/children component)]
    (into
      [:> Dialog (utils/assoc-some {}
                   :open (utils/model->value open)
                   :draggable draggable
                   :resizable resizable
                   :stretch stretch
                   :state state
                   :preventFocusRestore prevent-focus-restore
                   :initialFocus initial-focus
                   :headerText header-text
                   :header (utils/value->element header)
                   :footer (utils/value->element footer)
                   :onAfterClose on-after-close
                   :onAfterOpen on-after-open
                   :onBeforeClose on-before-close
                   :onBeforeOpen on-before-open
                   :style style
                   :className class
                   :accessibleName accessible-name
                   :accessibleNameRef accessible-name-ref
                   :accessibleRole accessible-role)]
      children)))