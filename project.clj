(defproject deeppocket "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [http-kit "2.1.18"]
                 [com.taoensso/timbre "4.2.0"]
                 [hiccup "1.0.5"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [environ "1.0.1"]
                 [cheshire "5.5.0"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-environ "1.0.1"]]
  :ring { :handler deeppocket.handler/app
          :init deeppocket.handler/init }
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
