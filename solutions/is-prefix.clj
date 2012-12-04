;; Anything you type in here will be executed 
;; immediately with the results shown on the 
;; right.

(def a [1 2 3])
(def b [1 2 3 4 5 6 7])
(def c [:a :b :c :d])

(def shorter-than
  (fn [list1 list2]
    (< (count list1) (count list2))
  )
)

(shorter-than b c)

(def first-pairs
  (fn [list1 list2]
    (partition 2 (interleave list1 list2))
  )
)

(first-pairs a c)

(def compare-pairs
  (fn [pairs-list]
    (map
      (fn [pair] (= (first pair)(second pair)))
      pairs-list
    )
  )
)

(compare-pairs (first-pairs a c))

(filter true?
     (compare-pairs (first-pairs a b))
  )

(def equal-pairs-number
  (fn
    [list1 list2]
    (filter true?
      (compare-pairs (first-pairs list1 list2))
    )
  )
)

(equal-pairs-number a b)

(def is-prefix?
  (fn 
    [list1 list2]
    (and
      (shorter-than list1 list2)
    (= 
      (count list1)
     (count (equal-pairs-number list1 list2))
    )
     )
  )
)

(is-prefix? a b)




(doc reduce)





