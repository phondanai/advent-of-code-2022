(def value-map
  {\A 1 \X 1     ; rock
   \B 2 \Y 2     ; paper
   \C 3 \Z 3})   ; scisor

(def input
  (-> "input-1.txt"
      (slurp)
      (clojure.string/split-lines)))

(defn calculate-score [[elf user]]
  (cond
    (= elf user) 3
    (=
     (mod elf 3) (dec user)) 6
    :else 0))

(let [user-input (map #(get value-map (last %)) input)
      elf-input (map #(get value-map (first %)) input)
      elf-user (map vector elf-input user-input)
      user-points (reduce + user-input)
      points (reduce + (map #(calculate-score %) elf-user))]
  (+ points user-points))

;; part-2
(def user-point-mapper {\X 0 \Y 3 \Z 6})

(defn calculate-input [[elf user]]
  (cond
    (= user 3) elf
    (= user 6) (inc (mod elf 3))
    :else (cond
            (= elf 1) 3
            :else (dec elf))))

(let [round-input(map #(get user-point-mapper (last %)) input)
      round-point (reduce + round-input)
      elf-input (map #(get value-map (first %)) input)
      elf-user (map vector elf-input round-input)
      user-point (reduce + (map #(calculate-input %) elf-user))]
  (+ user-point round-point))
