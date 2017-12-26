(ns my-exercise.routes.search
  (:require [hiccup.page :refer [html5]]
            [my-exercise.routes.defaults :refer [header address-form]]))

(defn page [request]
  (let [{:keys [street street-2 city state zip]} (:params request)]
    (html5
      (header request)
      (str "vals: " street " " street-2 " " city " " state " " zip))))
