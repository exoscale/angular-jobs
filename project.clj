(defproject io.exo/jobs "0.5.0"
  :description "example rest-api backed angularjs app"
  :url "https://github.com/exoscale/jobs"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main io.exo.jobs
  :plugins [[lein-marginalia "0.7.1"]]
  :dependencies [[org.clojure/clojure       "1.5.1"]
                 [http-kit                  "2.1.13"]
                 [compojure                 "1.1.6"]
                 [ring/ring-json            "0.2.0"]
                 [javax.servlet/servlet-api "2.5"]])
