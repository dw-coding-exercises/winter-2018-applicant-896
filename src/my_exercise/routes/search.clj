(ns my-exercise.routes.search
  (:require [hiccup.page :refer [html5]]
            [clj-http.client :as http]
            [my-exercise.routes.defaults :refer [header address-form]]
            [my-exercise.ocd-id :as ocd]))

(def api-base "https://api.turbovote.org/elections/upcoming")

(defn page [request]
  (let [{:keys [street street-2 city state zip]} (:params request)
        ocd-ids (ocd/gen-us-ids state city)
        ocd-id-string (clojure.string/join "," ocd-ids)
        resp (http/get api-base {:query-params {"district-divisions" ocd-id-string}})
        results (clojure.edn/read-string (:body resp))]
    (html5
      (header request)
      (str results))))
