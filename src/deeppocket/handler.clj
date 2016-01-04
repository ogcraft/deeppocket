(ns deeppocket.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [taoensso.timbre :as log]
            [environ.core :refer [env]]
            [deeppocket.routes :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn init []
  (log/info "deeppocket app initialization")
  (log/info "pocket-consumer-key" (env :pocket-consumer-key)))

(defroutes app-routes
  (route/not-found "Not Found"))

(def app
  (-> (routes deeppocket-routes app-routes)
  (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))
