(ns ui5-cljs.table
  (:require
   [reagent.core :as r]
   [applied-science.js-interop :as j]
   [ui5-cljs.utils :as utils]
   ["@ui5/webcomponents-react" :refer [Table TableRow TableGroupRow TableColumn TableCell]]))


(defn render-row
  [{:keys [navigated selected type id style class cells]}]
  (into
    [:> TableRow (utils/assoc-some {}
                   :data-id id
                   :navigated navigated
                   :selected selected
                   :type type
                   :style style
                   :className class)]
    (map (fn [{:keys [style class content]}]
           [:> TableCell (utils/assoc-some {}
                           :style style
                           :className class)
            content]))
    cells))


(defn render-column
  [{:keys [demand-popin popin-display popin-text min-width content style class]}]
  [:> TableColumn (utils/assoc-some {}
                    :demandPopin demand-popin
                    :popinDisplay popin-display
                    :popinText popin-text
                    :minWidth min-width
                    :style style
                    :className class)
   content])


(defn model->row [columns row]
  (let [{:row/keys [props]} row]
    (->> columns
         (mapv (fn [column]
                 (let [cell (get row column)]
                   (if (map? cell)
                     (merge (:cell/props cell)
                            {:content (:content cell)})
                     {:content cell}))))
         (hash-map :cells)
         (merge props))))


(defn table
  [{:keys [rows columns growing busy hide-no-data sticky-column-header mode
           growing-button-subtext growing-button-text busy-delay no-data-text on-load-more
           on-popin-change on-row-click on-selection-change accessible-name accessible-name-ref
           style class]}]
  (let [columns    (utils/model->value columns)
        rows       (utils/model->value rows)
        col-names  (mapv :name columns)
        render-row (comp render-row (partial model->row col-names))]
    (into
      [:> Table (utils/assoc-some {:columns (->> columns
                                                 (into [:<>] (map render-column))
                                                 (r/as-element))}
                  :busy (utils/model->value busy)
                  :stickyColumnHeader (utils/model->value sticky-column-header)
                  :growing growing
                  :hideNoData hide-no-data
                  :mode mode
                  :growingButtonSubtext growing-button-subtext
                  :growingButtonText growing-button-text
                  :busyDelay busy-delay
                  :noDataText no-data-text
                  :onLoadMore on-load-more
                  :onPopinChange (when (fn? on-popin-change)
                                   (fn [event]
                                     (let [popped-columns (j/get-in event [:detail :poppedColumns])
                                           columns        (mapv (fn [column]
                                                                  (let [text (j/get column :popinText)]
                                                                    (utils/find-by :popin-text text columns)))
                                                                popped-columns)]
                                       (on-popin-change columns))))
                  :onRowClick (when (fn? on-row-click)
                                (fn [event]
                                  (let [id  (j/get-in event [:detail :row :dataset :id])
                                        row (utils/find-by [:row/props :id] id rows)]
                                    (on-row-click row))))
                  :onSelectionChange (when (fn? on-selection-change)
                                       (fn [event]
                                         (let [selected-rows (j/get-in event [:detail :selectedRows])
                                               rows          (mapv (fn [row]
                                                                     (let [id (j/get-in row [:dataset :id])]
                                                                       (utils/find-by [:row/props :id] id rows)))
                                                                   selected-rows)]
                                           (on-selection-change rows))))
                  :accessibleName accessible-name
                  :accessibleNameRef accessible-name-ref
                  :style style
                  :className class)]
      (map (fn [row-or-group]
             (if (vector? row-or-group)
               (into
                 [:> TableGroupRow]
                 (map render-row row-or-group))
               (render-row row-or-group))))
      rows)))