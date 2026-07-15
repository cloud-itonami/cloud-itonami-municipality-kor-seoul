(ns ordinance.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest seoul-has-spec-basis
  (let [sb (facts/spec-basis "seoul")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://legal.seoul.go.kr/") sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "busan")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["seoul" "busan"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["busan"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["seoul.personal-information-protection-ordinance"]
         (mapv :ordinance/id (facts/by-topic "seoul" :data-protection))))
  (is (empty? (facts/by-topic "seoul" :labor)))
  (is (empty? (facts/by-topic "busan" :transparency))))
