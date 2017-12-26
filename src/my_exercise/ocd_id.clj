(ns my-exercise.ocd-id
  (:require [clojure.string :as str]))

(defn gen-us-ids
  "generate ocd-ids for the US"
  ([state place]
   (let [state-only (str "ocd-division/country:us/state:" (str/lower-case state))]
     (if (str/blank? place)
       [state-only]
       [state-only (str state-only
                        "/place:"
                        (-> place str/lower-case (str/replace #" " "_")))])))
  ([state]
   (gen-us-ids state nil)))