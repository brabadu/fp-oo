;; Anything you type in here will be executed 
;; immediately with the results shown on the 
;; right.

(use 'clojure.algo.monads)


;; Error utilities

(def oops!
     (fn [reason & args]
       (with-meta (merge {:reason reason}
                         (apply hash-map args))
                  {:type :error})))

(def oopsie?
     (fn [value]
       (= (type value) :error)))


(def decider
     (fn [value continuation]
       (if (oopsie? value)
         (:reason value)
         (continuation value))))

(def maybe-monad
     (monad [m-result identity
             m-bind   decider]))

(with-monad maybe-monad
  (domonad [a nil
            b (+ 1 a)] ; would blow up
     b))


(def factorial (fn [n]
     (cond (< n 0)
           (oops! "Factorial can never be less than zero." :number n)

           (< n 2) 1
           
           :else
           (* n (factorial (dec n))))))


(with-monad maybe-monad
  (domonad [a (factorial 5)
            b (+ 1 a)] ; would blow up
     b))


