(ns my-exercise.ocd-id
  (:require [clojure.string :as str]
            [clj-http.client :as http]))

(defn gen-us-ids
  "generate ocd-ids for the US
  todo: check that state is valid"
  ([state place]
   (let [state-only (str "ocd-division/country:us/state:" (str/lower-case state))]
     (if (str/blank? place)
       [state-only]
       [state-only (str state-only
                        "/place:"
                        (-> place str/lower-case (str/replace #" " "_")))])))
  ([state]
   (gen-us-ids state nil)))



;; WIP for pulling in geocoded data, This would allow me to handle pulling in county information (and maybe districts)
;; stopped working on it because the 2 hours were up

;(def geocoder-api-key "API_KEY_HERE")
;(def geocoder-api-base "https://maps.googleapis.com/maps/api/geocode/json")
;
;(defn fetch-geocode-data [address]
;  (http/get geocoder-api-base {:query-params {"address" address "key" geocoder-api-key}}))
;
;(defn gen-us-ids-geocoded [address]
;  nil)