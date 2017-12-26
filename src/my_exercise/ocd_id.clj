(ns my-exercise.ocd-id
  (:require [clojure.string :as str]))

(defn gen-us-id
  "generate ocd-ids for the US"
  ([state place]
   (cond-> "ocd-division/country:us/state:"
           (not (str/blank? state))
           (str (str/lower-case state))

           (and (not (str/blank? state)) (not (str/blank? place)))
           (str "/place:" (-> place str/lower-case (str/replace #" " "_")))))
  ([state]
   (gen-us-id state nil)))