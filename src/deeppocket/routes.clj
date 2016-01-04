(ns deeppocket.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [org.httpkit.client :as http]
            [environ.core :refer [env]]
            [deeppocket.utils :as utils]
            [taoensso.timbre :as log])
  (:import [java.util UUID]))

(def client-id         (env :pocket-consumer-key))
(def client-secret     (env :pocket-client-secret))
;; https://getpocket.com/v3/oauth/request
(def pocket-oauth-server "https://getpocket.com")
(def pocket-auth-request-url (str pocket-oauth-server "/v3/oauth/request"))

(defn what-is-my-ip [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (:remote-addr request)})

(defn obtain-pocket-request-token [consumer-key redirect-uri state]
  (let [{:keys [status headers body error] :as resp}
        @(http/get "https://getpocket.com/v3/oauth/request")]
    (if error
      (println "Failed, exception: " error)
      (println "HTTP GET success: " status))))

(defn login [request]
   (let [state (str (UUID/randomUUID))]
         (log/info "login function state: " state)
         (println "Incoming Request:")
         (clojure.pprint/pprint
           (obtain-pocket-request-token client-id "" state))
         (str "Ok")))

(defn logout [request]
  (log/info "logout"))

(defn oauth-redirect [request]
  (log/info "oauth-redirect"))


;;;;;;;;;;;;;;;;;;; routes ;;;;;;;;;;;;;;;;;;;;;;;;

(defroutes deeppocket-routes
  ;; Oauth routes
  (GET "/login"          [] login)
  (GET "/oauth-redirect" [] oauth-redirect)

  ;; Logout (yeah, this really shouldn't be a GET...)
  (GET "/logout"         [] logout)

  (GET "/" [] "Hello from Deeppocket routes"))
