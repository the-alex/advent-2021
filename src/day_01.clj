(ns day-01
  "A new dawn; a new day. A new life."
  (:require [clojure.java.io :as io]
            [cuerdas.core :as str]
            [clojure.edn :as edn]))

(defn parse-input
  "Read the input string, a newline delimited list of integers."
  [in]
  (->> in
       str/split
       (map #(edn/read-string %))))

(defn count-ascending
  "How often does our sonar scan a greater depth than the previous measure?"
  [measures]
  (->> measures
       (partition 2 1)
       (map (partial apply <))
       (filter true?)
       count))

(defn p1 [input] (count-ascending input))

(defn p2
  "Let's use the average of a sliding window to smooth our measurements."
  [input {:keys [window-size]
          :or {window-size 2}
          :as opts}]
  (->> input
       (partition window-size 1)
       (map (partial apply +))
       count-ascending))

(comment
  (let [input (-> "day_01_input.txt"
                  io/resource
                  slurp
                  parse-input)]

    {:p1? (p1 input)
     :p2? (p2 input {:window-size 3})}))
