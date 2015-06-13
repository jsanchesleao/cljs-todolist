(ns todolist.core
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.util.response :refer [file-response]]))

(defn index []
  (file-response "public/html/index.html" {:root "resources"}))

(defn teste []
  {:status 200
   :headers {}
   :body "Hello There!!"})

(defroutes routes
  (GET "/" [] (index))
  (GET "/teste" [] (teste))
  (route/files "/" {:root "resources/public"}))

(def handler routes)