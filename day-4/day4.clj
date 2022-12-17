(require '[clojure.set])

(def input "2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8")

; (def input (slurp "input.txt"))

(defn is-cover? [[x y]]
  (let [first-term (clojure.string/split x #"-")
        second-term (clojure.string/split y #"-")
        first-pair (set
                    (range
                      (Integer/parseInt (first first-term))
                      (inc (Integer/parseInt (second first-term)))))
        second-pair (set
                    (range
                      (Integer/parseInt (first second-term))
                      (inc (Integer/parseInt (second second-term)))))
        intersect (clojure.set/intersection first-pair second-pair)]
    (or
     (= intersect first-pair)
     (= intersect second-pair))))

;; part 1
(count
  (filter identity
    (map #(is-cover? %)
      (->> input
          (clojure.string/split-lines)
          (map #(clojure.string/split % #","))))))

;; part 2
(defn is-overlap? [[x y]]
  (let [first-term (clojure.string/split x #"-")
        second-term (clojure.string/split y #"-")
        first-pair (set
                    (range
                     (Integer/parseInt (first first-term))
                     (inc (Integer/parseInt (second first-term)))))
        second-pair (set
                     (range
                      (Integer/parseInt (first second-term))
                      (inc (Integer/parseInt (second second-term)))))
        intersect (clojure.set/intersection first-pair second-pair)]
    (not= intersect #{})))

(count
 (filter identity
         (map #(is-overlap? %)
              (->> input
                   (clojure.string/split-lines)
                   (map #(clojure.string/split % #","))))))
