(ns ui5-cljs.message
  (:require
   [reagent.core :as r]
   [ui5-cljs.utils :as utils]
   ["@ui5/webcomponents-react" :refer [MessageStrip]]))


(defn message [{:keys [design icon on-close hide-icon hide-close-button style class-name]}]
  (let [component (r/current-component)
        children  (r/children component)]
    (into
      [:> MessageStrip (utils/assoc-some {}
                         :design design
                         :icon (utils/value->element icon)
                         :hideIcon hide-icon
                         :hideCloseButton hide-close-button
                         :onClose on-close
                         :style style
                         :className class-name)]
      children)))