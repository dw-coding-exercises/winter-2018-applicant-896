(ns my-exercise.routes.search
  (:require [hiccup.page :refer [html5]]
            [clj-http.client :as http]
            [my-exercise.routes.defaults :refer [header address-form]]
            [my-exercise.ocd-id :as ocd]))

(def api-base "https://api.turbovote.org/elections/upcoming")

(defn results-view
  "render the search results
  todo: better styling
  todo: add UI displaying the different methods for voting and registering"
  [address results]
  [:div
   [:h2 (str (count results) " Results for address: " address)]
   (for [result results]
     [:div [:h4 (:description result)]
      [:span (str "Date to vote: " (:date result))]])])

(defn page [request]
  (let [{:keys [street street-2 city state zip]} (:params request)
        address (str street " "
                     (if-not (clojure.string/blank? street-2)
                       (str street-2 " ")
                       "")
                     city ", " state " " zip)
        ocd-ids (ocd/gen-us-ids state city)
        ocd-id-string (clojure.string/join "," ocd-ids)
        resp (http/get api-base {:query-params {"district-divisions" ocd-id-string}})
        results (clojure.edn/read-string (:body resp))]
    (html5
      (header request)
      (results-view address results)
      [:hr]
      [:a {:href "/#address-form"} [:h3 "Try Another Search"]])))
