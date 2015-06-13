(ns todolist.components
  (:require [todolist.actions :as actions]))

(defn todo-item [state todo] 
  (let [complete   (= :done (:status todo))
        uncomplete (= :todo (:status todo))
        complete-todo! (fn [_]
          (actions/complete-todo! state todo))
        uncomplete-todo! (fn [_]
          (actions/uncomplete-todo! state todo))]
  [:li {:class (-> todo :status (name))}
    (:text todo)
    (cond 
      uncomplete  [:button.complete-todo {:on-click complete-todo!} "Done"]
      complete    [:button.uncomplete-todo {:on-click uncomplete-todo!} "Undo"])]))

(defn when-enter [fun]
  (fn [e] 
    (when (= "Enter" (.-key e))
      (fun e))))

(defn todo-list [state] 
  (let [next-todo (:next-todo @state)
        update-next-todo! (fn [e]
          (swap! state assoc-in [:next-todo] (-> e .-target .-value)))
        add-todo! (fn [_]
          (actions/add-todo! state (:next-todo @state))
          (swap! state assoc-in [:next-todo] ""))
        remove-done! (fn [_]
          (actions/remove-done! state))]
  [:div.todo-list
    [:ul
      (for [todo (:todos @state)]
      ^{:key todo} [todo-item state todo])]
      [:input {:type "text"
               :value next-todo
               :on-key-down (when-enter add-todo!)
               :on-change update-next-todo!}]
      [:button.add-todo {:on-click add-todo!} "Add"]
      [:a.remove-done {:href "#"
                       :on-click remove-done!} "Remove Done"]]))