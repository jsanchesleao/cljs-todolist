(ns todolist.storage)

(defn- to-clj [js-data]
  (js->clj js-data :keywordize-keys true))

(defn storage-get! [key]
  (->> key
    (.getItem (.-localStorage js/window))
    (.parse js/JSON)
    (to-clj)))

(defn storage-set! [key value]
  (->> value
    (clj->js)
    (.stringify js/JSON)
    (.setItem js/localStorage key)))

