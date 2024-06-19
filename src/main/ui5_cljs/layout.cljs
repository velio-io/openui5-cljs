(ns ui5-cljs.layout
  (:require
   [clojure.string :as string]
   [reagent.core :as r]
   [ui5-cljs.utils :as utils]
   ["@ui5/webcomponents-react" :refer [FlexBox]]))


(defn gutter? [gutter]
  (and (some? gutter) (> gutter 0)))


(defn prep-props [axis gutter idx length props]
  (let [props' (assoc props :gutter gutter)]
    (if (and (gutter? gutter) (> length 1))
      (let [gutter-size (str (/ gutter 2) "px")
            start-attr  (if (= axis :vertical) :margin-bottom :margin-right)
            end-attr    (if (= axis :vertical) :margin-top :margin-left)]
        (cond-> props'
          (and (= idx 0) (> length 1)) (assoc start-attr gutter-size)
          (and (= idx (dec length)) (> length 1)) (assoc end-attr gutter-size)
          (and (> idx 0) (< idx (dec length))) (assoc end-attr gutter-size start-attr gutter-size)))
      props')))


(defn prep-child-components [axis gutter length child-comp]
  (map-indexed
   (fn [idx child]
     (if (vector? child)
       (let [[comp props & rest] child
             props-&-rest (if (map? props)
                            (cons (prep-props axis gutter idx length props) rest)
                            (cons (prep-props axis gutter idx length {})
                                  (cons props rest)))]
         (if (= comp child-comp)
           (vec (cons comp props-&-rest))
           ;; wrap child elements into desired component
           [child-comp (prep-props axis gutter idx length {})
            child]))
       child))))


(defn calc-width [gutter span]
  (if (and (some? gutter) (> gutter 0))
    (str "calc((100% - " (* 11 gutter) "px) * " (/ span 12) " + (" gutter "px * " (- span 1) "))")
    (str (* (/ 100 12) span) "%")))


(defn column [{:keys [gutter span margin-left margin-right style]
               :or   {span  1
                      style {}}}]
  (println (calc-width gutter span))
  (let [component (r/current-component)
        children  (r/children component)
        styles    (utils/assoc-some
                    (merge style
                           {:box-sizing "border-box"
                            :flex       (str span " " span " auto")
                            :width      (calc-width gutter span)})
                    :margin-left margin-left
                    :margin-right margin-right)]
    (into
      [:div {:style styles}]
      children)))


(defn row [{:keys [gutter align style margin-top margin-bottom]
            :or   {align "Stretch"
                   style {}}}]
  (let [component (r/current-component)
        children  (r/children component)
        length    (count children)
        styles    (utils/assoc-some style
                    :margin-top margin-top
                    :margin-bottom margin-bottom)]
    (into
      [:> FlexBox {:fitContainer true
                   :wrap         "NoWrap"
                   :alignItems   (string/capitalize align)
                   :style        styles}]
      (prep-child-components :horizontal gutter length column)
      children)))


(defn layout [{:keys [gutter as style class]}]
  (let [component (r/current-component)
        children  (r/children component)
        length    (count children)]
    (into
      [:> FlexBox (utils/assoc-some {:direction "Column"}
                    :as as
                    :style style
                    :className class)]
      (prep-child-components :vertical gutter length row)
      children)))