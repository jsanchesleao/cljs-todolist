(ns todolist.actions)

(defn increment [state]
  (swap! state update-in [:count] inc))

(defn add-todo! [state text]
  (let [todo {:text text :status :todo}]
    (swap! state update-in [:todos]
      #(conj % todo))))

(defn- update-todo-status [status]
  (fn [state todo]
    (swap! state update-in [:todos]
      #(map (fn [item]
        (if (= item todo)
          (assoc item :status status)
          item)) %))))

(def complete-todo! (update-todo-status :done))

(def uncomplete-todo! (update-todo-status :todo))

(defn- is-done [todo]
  (-> todo
      :status
      (not= :done)))

(defn remove-done! [state]
  (swap! state update-in [:todos]
    #(filter is-done %)))