(ns ui5-cljs.label
  (:require
   [reagent.core :as r]
   [ui5-cljs.utils :as utils]
   ["@ui5/webcomponents-react" :refer [Label]]))


(defn label
  [{:keys [for required colon wrapping-type style class]}]
  (let [component (r/current-component)
        children  (r/children component)]
    (into
      [:> Label (utils/assoc-some {}
                  :for for
                  :required required
                  :showColon colon
                  :wrappingType wrapping-type
                  :style style
                  :className class)]
      children)))