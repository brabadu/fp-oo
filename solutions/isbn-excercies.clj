;; Anything you type in here will be executed 
  ;; immediately with the results shown on the 
  ;; right.
  (use '[clojure.string :only [split]])
  
  (def myseq [4 8 9 3 2])
  
  (def reversed-digits
    (fn [string]
      (reverse
       (map (fn [digit-string] (Integer/parseInt digit-string))
            (rest (split string #""))))))
  
  (def enum (comp range count))
  (def enum1 (fn [arg] (map inc
                            (enum arg))))
  
  (def upc-enum (fn [arg]
                  (map (fn [i] (nth [3 1] (rem i 2)))
                       (enum1 arg)
                       )
                  )
    )
  
  (upc-enum myseq)
  
  (def check-sum
    (fn [enumerator seq]
    (apply +
           (map *
                (enumerator seq)
                seq
            ))))
  
  (check-sum enum1 myseq)
  
  (def number-checker (fn [enumerator divider]
                        (fn [string-to-check]
    (zero? (rem 
              (check-sum enumerator (reversed-digits string-to-check))
              divider)))))
  
  (def isbn? (number-checker enum1 11))
  
  (isbn? "0131774115")
  (isbn? "0977716614")
  (isbn? "1934356190")
  
  
  (def upc? (number-checker upc-enum 10))
  
  (upc? "074182265830")
  (upc? "731124100023")
  (upc? "722252601404")
