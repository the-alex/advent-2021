(ns day-01
  "It's a new dawn;
  a new day.
  A new life."
  (:require [clojure.java.io :as io]
            [cuerdas.core :as str]
            [wing.core :as w]
            [clojure.edn :as edn]))

(def ex-in
  "199
200
208
210
200
207
240
269
260
263")

(defn parse-input [in]
  (->> in
       str/split
       (map #(edn/read-string %))))

(defn p1 [input]
  (->> input
       (partition 2 1)
       (map #(apply < %))
       (filter true?)
       count))

(defn p2 [input]
  (->> input
       (partition 3 1)
       (map (partial apply +))
       (partition 2 1)
       (map #(apply < %))
       (filter true?)
       count))

(let [input (-> #_ex-in
                (slurp (io/resource "day_01_input.txt"))
                parse-input)
      p1? (p1 input)
      p2? (p2 input)]

  (w/make-map p1? p2?))
