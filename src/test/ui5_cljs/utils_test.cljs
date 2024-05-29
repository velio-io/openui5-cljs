(ns ui5-cljs.utils-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [reagent.core :as r]
   [ui5-cljs.utils :as utils]))


(deftest test-assoc-some
  (is (= {:a 1 :b 2 :c 3}
         (utils/assoc-some {:a 1 :b 2} :c 3)))
  (is (= {:a 1 :b 2 :c 3 :d 4}
         (utils/assoc-some {:a 1 :b 2} :c 3 :d 4)))
  (is (= {:a 1 :b 2 :c 3 :d 4}
         (utils/assoc-some {:a 1 :b 2} :c 3 :d 4 :e nil))))


(deftest test-atom?
  (is (utils/atom? (r/atom 1)))
  (is (utils/atom? (atom nil)))
  (is (not (utils/atom? {:a 1})))
  (is (not (utils/atom? [1 2 3])))
  (is (not (utils/atom? "string")))
  (is (not (utils/atom? 1)))
  (is (not (utils/atom? nil))))


(deftest test-model->value
  (is (= 1 (utils/model->value 1)))
  (is (= 1 (utils/model->value (r/atom 1))))
  (is (= 1 (utils/model->value (atom 1))))
  (is (= {:a 1} (utils/model->value {:a 1})))
  (is (= [1 2 3] (utils/model->value [1 2 3])))
  (is (= "string" (utils/model->value "string")))
  (is (= nil (utils/model->value nil))))


(deftest test-value->element
  (let [element (utils/value->element [:h1 "Hello, world!"])]
    (is (= "h1" (.-type element)))
    (is (= "Hello, world!" (.. element -props -children))))

  (is (= 1 (utils/value->element 1)))
  (is (= {:a 1} (utils/value->element {:a 1})))
  (is (= "string" (utils/value->element "string")))
  (is (= nil (utils/value->element nil))))


(deftest test-uget
  (is (= 1 (utils/uget {:a 1} :a)))
  (is (= 1 (utils/uget #js {:a 1} "a")))
  (is (= 1 (utils/uget #js {:a 1} :a)))
  (is (= nil (utils/uget nil :a)))
  (is (= nil (utils/uget nil 0))))


(deftest test-find-by
  (is (= {:a 1} (utils/find-by :a 1 [{:a 1} {:a 2} {:a 3}])))
  (is (= {:a 2} (utils/find-by :a 2 [{:a 1} {:a 2} {:a 3}])))
  (is (= nil (utils/find-by :a 4 [{:a 1} {:a 2} {:a 3}])))
  (is (= nil (utils/find-by :b 1 [{:a 1} {:a 2} {:a 3}])))
  (is (= nil (utils/find-by :a 1 nil)))
  (is (= nil (utils/find-by :a 1 [])))
  (is (= nil (utils/find-by :a 1 "string"))))
