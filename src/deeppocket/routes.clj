(ns deeppocket.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [taoensso.timbre :as log]))

(defroutes deeppocket-routes
  (GET "/" [] "Hello from Deeppocket routes"))
