(ns problem-4.core
  (:require [clojure.string :as str]))

(defn palindrome?
  "Finds if string is a palindrome"
  [s]
  (= s (str/reverse s)))

(def three-digit-palindromes
  (for [n1 (range 999 99 -1)
        n2 (range n1 99 -1)
        :let [product (* n1 n2)]
        :when (palindrome? (str product))]
    [product n1 n2]))

(defn problem-4 []
  (->> three-digit-palindromes
       (sort-by first)
       last))

#_(problem-4)

