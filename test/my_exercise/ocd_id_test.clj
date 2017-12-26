(ns my-exercise.ocd-id-test
  (:require [clojure.test :refer :all]
            [my-exercise.ocd-id :refer :all]))


(deftest gen-us-ids-test
  (testing "simple ocd id generation with only state"
    (is (= ["ocd-division/country:us/state:nj"]
           (gen-us-ids "NJ"))))
  (testing "simple ocd id generation with state and place"
    (is (= ["ocd-division/country:us/state:nj" "ocd-division/country:us/state:nj/place:union"]
           (gen-us-ids "NJ" "Union"))))
  (testing "simple ocd id generation with state and place having spaces"
    (is (= ["ocd-division/country:us/state:nj" "ocd-division/country:us/state:nj/place:union_city"]
           (gen-us-ids "NJ" "Union City")))))