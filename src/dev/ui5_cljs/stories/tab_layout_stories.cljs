(ns ui5-cljs.stories.tab-layout-stories
  (:require
   [reagent.core :as r]
   [ui5-cljs.stories.utils :as utils]
   ["@ui5/webcomponents-icons/dist/menu"]
   ["@ui5/webcomponents-icons/dist/activities"]
   ["@ui5/webcomponents-icons/dist/add"]
   ["@ui5/webcomponents-icons/dist/employee"]
   ["@ui5/webcomponents-icons/dist/settings"]
   [ui5-cljs.tab-layout :refer [tab-layout tab tab-separator]]))


(def ^:export default
  (utils/->default
   {:title     "Tab Layout"
    :component tab-layout
    :argTypes  {:layout                    {:type         {:name "string" :required false}
                                            :description  "Defines the alignment of the content and the additionalText of a tab.
                                                           Note: The content and the additionalText would be displayed vertically by default,
                                                           but when set to Inline, they would be displayed horizontally."
                                            :defaultValue "Standard"
                                            :control      "select"
                                            :options      ["Standard", "Inline"]}
                :content-background-design {:type         {:name "string" :required false}
                                            :description  "Sets the background color of the Tab Container's content as Solid, Transparent, or Translucent."
                                            :defaultValue "Solid"
                                            :control      "select"
                                            :options      ["Solid", "Transparent", "Translucent"]}
                :header-background-design  {:type         {:name "string" :required false}
                                            :description  "Sets the background color of the Tab Container's header as Solid, Transparent, or Translucent."
                                            :defaultValue "Solid"
                                            :control      "select"
                                            :options      ["Solid", "Transparent", "Translucent"]}
                :overflow-button           {:type        {:name "component" :required false}
                                            :description "Defines the button which will open the overflow menu.
                                                          If nothing is provided to this slot, the default button will be used."
                                            :control     {:type nil}}
                :start-overflow-button     {:type        {:name "component" :required false}
                                            :description "Defines the button which will open the start overflow menu if available.
                                                          If nothing is provided to this slot, the default button will be used.\n\n"
                                            :control     {:type nil}}
                :on-tab-select             {:type        {:name "function" :required false}
                                            :description "Fired when a tab is selected."
                                            :control     {:type nil}}
                :collapsed                 {:type         {:name "boolean" :required false}
                                            :description  "Defines whether the tab content is collapsed."
                                            :defaultValue false
                                            :control      "boolean"}
                :fixed                     {:type         {:name "boolean" :required false}
                                            :description  "Defines whether the tabs are in a fixed state that is not expandable/collapsible by user interaction."
                                            :defaultValue false
                                            :control      "boolean"}
                :show-overflow             {:type         {:name "boolean" :required false}
                                            :description  "Defines whether the overflow select list is displayed.
                                                           The overflow select list represents a list,
                                                           where all tabs are displayed so that it's easier for the user to select a specific tab."
                                            :defaultValue false
                                            :control      "boolean"}
                :tabs-overflow-mode        {:type         {:name "string" :required false}
                                            :description  "Defines the overflow mode of the header (the tab strip).
                                                           If you have a large number of tabs, only the tabs that can fit on screen will be visible.
                                                           All other tabs that can 't fit on the screen are available in an overflow tab \"More\".
                                                           Note: Only one overflow at the end would be displayed by default, but when set to StartAndEnd,
                                                           there will be two overflows on both ends, and tab order will not change on tab selection."
                                            :defaultValue "End"
                                            :control      "select"
                                            :options      ["End", "StartAndEnd"]}
                :style                     {:type        {:name "map" :required false}
                                            :description "Element style which will be appended to the most outer element of a component. Use this prop carefully, some css properties might break the component."
                                            :control     {:type nil}}
                :class                     {:type        {:name "string" :required false}
                                            :description "CSS Class Name which will be appended to the most outer element of a component. Use this prop carefully, overwriting CSS rules might break the component."
                                            :control     "text"}
                :tab->id                   {:type        {:name "string" :required false}
                                            :description "The unique identifier of the tab."
                                            :control     "text"}
                :tab->text                 {:type        {:name "string" :required false}
                                            :description "The text to be displayed for the item."
                                            :control     "text"}
                :tab->additional-text      {:type        {:name "string" :required false}
                                            :description "Represents the \"additionalText\" text, which is displayed in the tab.
                                                          In the cases when in the same time there are tabs with icons and tabs without icons,
                                                          if a tab has no icon the \"additionalText\" is displayed larger."
                                            :control     "text"}
                :tab->design               {:type         {:name "string" :required false}
                                            :description  "Defines the component's design color.
                                                           The design is applied to:
                                                           the component icon
                                                           the text when the component overflows
                                                           the tab selection line
                                                           Available designs are: \"Default\", \"Neutral\", \"Positive\", \"Critical\" and \"Negative\".
                                                           Note: The design depends on the current theme."
                                            :defaultValue "Default"
                                            :control      "select"
                                            :options      ["Positive" "Negative" "Critical" "Neutral" "Default"]}
                :tab->icon                 {:type        {:name "string" :required false}
                                            :description "Defines the icon source URI to be displayed as graphical element within the component.
                                                          The SAP-icons font provides numerous built-in icons."
                                            :control     "text"}
                :tab->sub-tabs             {:type        {:name "collection" :required false}
                                            :description "Defines hierarchies with nested sub tabs.
                                                          A vector of maps with the following keys:
                                                          - id: string, the unique identifier of the tab.
                                                          - text: string, the text to be displayed for the item.
                                                          - content: reagent element, the content to be displayed for the item.
                                                          - icon: string, the icon source URI to be displayed as graphical element within the component.
                                                          - disabled: boolean, determines whether the component is displayed as disabled.
                                                          - selected: boolean, determines whether the component is selected."
                                            :control     {:type nil}}
                :tab->disabled             {:type         {:name "boolean" :required false}
                                            :description  "Disabled tabs can't be selected."
                                            :defaultValue false
                                            :control      "boolean"}
                :tab->selected             {:type         {:name "boolean" :required false}
                                            :description  "Specifies if the component is selected."
                                            :defaultValue false
                                            :control      "boolean"}}}))


(defn ^:export tab-layout-basic [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [tab-layout (merge {:layout                    "Standard"
                         :content-background-design "Solid"
                         :header-background-design  "Solid"
                         :on-tab-select             (fn [_event {:keys [id index]}]
                                                      (js/console.log "selected tab is" id "index" index))}
                        params)
      [tab {:id              "tab-1"
            :text            "Tab One"
            :additional-text "5"
            :icon            "menu"
            :selected        true}
       [:div "Content 1"]]
      [tab {:id              "tab-2"
            :text            "Tab Two"
            :additional-text "20"
            :icon            "activities"}
       [:div "Content 2"]]
      [tab {:id   "tab-3"
            :text "Tab Three"
            :icon "add"}
       [:div "Content 3"]]
      [tab {:id   "tab-4"
            :text "Tab Four"
            :icon "employee"}
       [:div "Content 4"]]
      [tab {:id   "tab-5"
            :text "Tab Five"
            :icon "settings"}
       [:div "Content 5"]]])))


(defn ^:export tab-layout-with-separator [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [tab-layout (merge {:layout                    "Standard"
                         :content-background-design "Solid"
                         :header-background-design  "Solid"
                         :on-tab-select             (fn [_event {:keys [id index]}]
                                                      (js/console.log "selected tab is" id "index" index))}
                        params)
      [tab {:text "Tab One" :selected true}
       [:div "Content 1"]]
      [tab-separator]
      [tab {:text "Tab Two"}
       [:div "Content 2"]]])))


(defn ^:export tab-layout-nested-tabs [args]
  (let [params (-> args utils/->params)]
    (r/as-element
     [tab-layout (merge {:layout                    "Standard"
                         :content-background-design "Solid"
                         :header-background-design  "Solid"
                         :on-tab-select             (fn [_event {:keys [id index]}]
                                                      (js/console.log "selected tab is" id "index" index))}
                        params)
      [tab {:text     "Tab One"
            :selected true
            :sub-tabs [{:text "Sub Tab 1.1" :content [:div "Sub Content 1.1"]}
                       {:text "Sub Tab 1.2" :content [:div "Sub Content 1.2"]}]}
       [:div "Content 1"]]
      [tab-separator]
      [tab {:text "Tab Two"}
       [:div "Content 2"]]
      [tab {:text     "Tab Three"
            :sub-tabs [{:text "Sub Tab 3.1" :content [:div "Sub Content 3.1"]}
                       {:text "Sub Tab 3.2" :content [:div "Sub Content 3.2"]}]}
       [:div "Content 3"]]])))
