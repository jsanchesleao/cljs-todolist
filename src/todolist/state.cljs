(ns todolist.state
  (:require [reagent.core :refer [atom]]))

(defonce app-state (atom 
  {:todos [{:text "test" :status :todo}
           {:text "easy" :status :done}]
   :next-todo ""
  }))