(ns ui5-cljs.card
  (:require
   [reagent.core :as r]
   ["@ui5/webcomponents-react" :refer [Card CardHeader AnalyticalCardHeader NumericSideIndicator]]
   [ui5-cljs.utils :as utils]))


(defn numeric-side-indicator
  [{:keys [number title state unit style class]}]
  [:> NumericSideIndicator
   (utils/assoc-some {:number    number
                      :titleText title}
     :state state
     :unit unit
     :style style
     :className class)])


(defn analytical-card-header
  [{:keys [title subtitle trend value scale state description unit status style class on-click children]}]
  (utils/value->element
   (into
     [:> AnalyticalCardHeader
      (utils/assoc-some {}
        :titleText title
        :subtitleText subtitle
        :trend trend
        :value value
        :scale scale
        :state state
        :status status
        :description description
        :unitOfMeasurement unit
        :style style
        :className class
        :onClick on-click)]
     children)))


(defn card-header
  [{:keys [title subtitle status avatar action style class interactive on-click]}]
  (utils/value->element
   [:> CardHeader
    (utils/assoc-some {}
      :titleText title
      :subtitleText subtitle
      :status status
      :style style
      :className class
      :avatar (utils/value->element avatar)
      :action (utils/value->element action)
      :interactive interactive
      :onClick on-click)]))


(defn card
  [{:keys [header style class accessible-name accessible-name-ref]}]
  (let [component (r/current-component)
        children  (r/children component)
        header    (when (some? header)
                    (if (:analytical header)
                      (analytical-card-header header)
                      (card-header header)))]
    (into
      [:> Card
       (utils/assoc-some {}
         :header header
         :style style
         :className class
         :accessibleName accessible-name
         :accessibleNameRef accessible-name-ref)]
      children)))