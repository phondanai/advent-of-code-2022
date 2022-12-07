(def input "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw")

(def priority
  (let [AZ (map char (range (int \A) (inc (int \Z))))
        az (map char (range (int \a) (inc (int \z))))
        AZaz (flatten (merge AZ az))
        mapping (zipmap AZaz (range 1 53))]
    mapping))

(comment 
  (def input (slurp "input.txt")))

(def input-vec (clojure.string/split-lines input))

(defn split-2 [x]
   (let [half (/ (count x) 2)
         part-1 (subs x 0 half)
         part-2 (subs x half (count x))]
     (clojure.set/intersection (set part-1) (set part-2))))

(let [commons (map #(split-2 %) input-vec)
      points (reduce + (map #(get priority (first %)) commons))]
  points)


;; part-2

(def input-vec-3 (partition 3 input-vec))

(defn find-common [[part-1 part-2 part-3]]
  (clojure.set/intersection (set part-1) (set part-2) (set part-3)))

(let [commons (map #(find-common %) input-vec-3)
      points (reduce + (map #(get priority (first %)) commons))]
  points)
