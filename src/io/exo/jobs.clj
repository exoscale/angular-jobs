(ns io.exo.jobs
  "Simplistic JSON API for a Job board"
  (:require [org.httpkit.server   :as server]
            [ring.middleware.json :as json]
            [compojure.handler    :as handler]
            [compojure.route      :as route]
            [compojure.core       :refer [defroutes GET POST DELETE context]]
            [ring.util.response   :refer [response content-type redirect]]))

(def ^{:doc "Atom holding our jobs"} db (atom {}))

(defn create!
  "Insert a new job with a random ID, yields the updated job map"
  [job]
  (let [id (str (java.util.UUID/randomUUID))]
    (swap! db assoc id (assoc job :id id))))

(defn delete!
  "Remove a job, yields the updated map"
  [id]
  (swap! db dissoc id))

(defn mksite
  "Build a handler for our API routes."
  [routes]
  (-> routes
      (handler/api)
      (json/wrap-json-body {:keywords? true})
      (json/wrap-json-response)))

(defroutes job-api
  (GET  "/" []             (redirect "/index.html"))
  (GET  "/jobs" []         (response @db))
  (POST "/jobs" req        (response (create! (:body req))))
  (DELETE "/jobs/:id" [id] (response (delete! id)))
  (route/resources         "/")
  (route/not-found         "<html><h2>404</h2></html>"))

(defn -main
  "Start the API"
  [& args]
  (server/run-server (mksite job-api) {:port 8080}))
