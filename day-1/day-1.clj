(def not-blank? (complement clojure.string/blank?))
(let [input (->> "input-1.txt"
                 (slurp)
                 (clojure.string/split-lines))
      clean-input (->> input
                       (partition-by #(= % ""))
                       (filter #(not-blank? (first %))))
      summing (for [y clean-input]
                (reduce + (map #(Integer/parseInt %) y)))
      max-cal (apply max summing)
      top3 (reduce + (take 3 (sort > summing)))]
  (println {:max-cal max-cal :top3 top3}))
