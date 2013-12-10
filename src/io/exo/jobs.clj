(ns io.exo.jobs
  "Simplistic JSON API for a Job board. No validation"
  (:require [org.httpkit.server   :as server]
            [ring.middleware.json :as json]
            [compojure.route      :as route]
            [compojure.core       :refer [defroutes GET POST DELETE]]
            [ring.util.response   :refer [response redirect]])
  (:gen-class))

(def ^{:doc "Atom holding our jobs"} db (atom {}))

(defn create!
  "Insert a new job with a random ID, yields the updated job map"
  [job]
  (let [id (str (java.util.UUID/randomUUID))]
    (swap! db assoc id job)))

(defn delete!
  "Remove a job, yields the updated map"
  [id]
  (swap! db dissoc id))

(defroutes api-routes
  "Main router: 3 REST routes and resource handlers."

  (GET  "/jobs" []         (response @db))
  (POST "/jobs" req        (response (create! (:body req))))
  (DELETE "/jobs/:id" [id] (response (delete! id)))

  (GET  "/" []             (redirect "/index.html"))
  (route/resources         "/")
  (route/not-found         "<html><h2>404</h2></html>"))

(defn -main
  "Start the API, forces port 8080"
  [& args]
  (let [api (-> api-routes (json/wrap-json-body) (json/wrap-json-response))
        cfg {:port 8080}]
    (server/run-server api cfg)
    (println "Job board API up and running on http://localhost:8080")))
