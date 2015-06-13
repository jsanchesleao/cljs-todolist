(ns todolist.state
  (:require [reagent.core :refer [atom]]
            [todolist.storage :refer [storage-get! storage-set!]]))

(defn- keywordize-todo [todo]
  (assoc todo :status (keyword (:status todo))))

(def default-state {:todos [] :next-todo ""})
  
(defn get-initial-state! []
  (try
    (->
      (storage-get! "app-state")
      (update-in [:todos] #(map keywordize-todo %)))
    (catch js/Error _ 
      (do
        (storage-set! "app-state" default-state)
        default-state))))

(def app-state (atom (get-initial-state!)))