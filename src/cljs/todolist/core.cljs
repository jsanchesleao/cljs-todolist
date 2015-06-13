(ns ^:figwheel-always todolist.core
    (:require [reagent.core :refer [render-component]]
              [todolist.state :refer [app-state]]
              [todolist.components :refer [todo-list]]))

(enable-console-print!)

(render-component
  [todo-list app-state]
  (. js/document (getElementById "app")))

(defn on-js-reload []) 

