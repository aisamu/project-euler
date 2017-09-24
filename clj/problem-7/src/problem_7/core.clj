(ns problem-7.core)

;; Naive, slow version from
;; https://stackoverflow.com/questions/31906443/functional-way-to-check-primality-of-a-number-in-clojure
(defn divisible? [a b]
  (zero? (mod a b)))

(defn prime? [n]
  (and (> n 1) (not-any? (partial divisible? n) (range 2 n))))


(def primes-list
  (filter prime? (range)))

(defn problem-7
  "Be patient"
  []
  (nth primes-list (dec 10001)))

#_(problem-7)

;; Sieve-based prime generator

(defn gen-primes
  "Generates an infinite, lazy sequence of prime numbers
   https://stackoverflow.com/questions/960980/fast-prime-number-generation-in-clojure
   https://web.archive.org/web/20150710134640/http://diditwith.net/2009/01/20/YAPESProblemSevenPart2.aspx"
  []
  (let [reinsert (fn [table x prime]
                   (update-in table [(+ prime x)] conj prime))]
    (defn primes-step [table d]
      (if-let [factors (get table d)]
        (recur (reduce #(reinsert %1 d %2) (dissoc table d) factors)
               (inc d))
        (lazy-seq (cons d (primes-step (assoc table (* d d) (list d))
                                       (inc d))))))
    (primes-step {} 2)))

(defn problem-7-fast
  []
  (-> (gen-primes)
      (nth (dec 10001))))

#_(problem-7-fast)
