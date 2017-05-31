(ns clj-number-game-third.handlers
	(:require [re-frame.core :as rf]
			  [clj_number_game_third.db :as db]
			  [clj_number_game_third.subs :as sb]))
;; reg db
(rf/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

;; god bless stack overflow for this function
;;(defn assoc-all
;;  [v ks value]
;;  (reduce #(assoc %1 %2 value) v ks))

(defn assoc-all
  [v ks value]
  (persistent!
    (reduce
      #(assoc! %1 %2 value)
      (transient v)
      ks)))

;;(assoc-in-all [1 2 3 4] [0 1] [1 2])
;; => [2 2 3 4]

(defn rand-tile [db]
	(let [empty-tiles- (filter #(= (:val (second %)) 0) (get-in db [:tiles]))]
			(if (not-empty empty-tiles-)
				(first (rand-nth empty-tiles-))
				0)))

(rf/reg-event-db
  :reset-tile
  (fn [db [_ from]]
  	(assoc-in db [:tiles from :val] 0)))

(rf/reg-event-db
  :apply-tile-change
  (fn [db [_ from to value]]
  	(let [tiles- (get-in db [:tiles ])]
		(assoc-in db [:tiles] (reduce (fn [tiles [index valuefn]]
												(if (= index to)
													(assoc tiles index (assoc valuefn :val value))
													(if (= index from)
														(assoc tiles index (assoc valuefn :val 0))
														(assoc tiles index (assoc valuefn :val (:val valuefn))))))
        									  {}
        									  tiles-)))))

(defn move-tiles [db direction]
	(let [tiles (get-in db [:tiles])
		  rows (get-in db [:rows direction])]
		  (doall
			  (for [row rows]
				(for [x (range 1 4)]
					(let [steps (atom x)
						  this-tile-val (:val (tiles (row x)))]
						(if-not (= this-tile-val 0)
							(while (pos? @steps)
								(do (let [compareable-tile (:val (tiles (row (- @steps 1)))) ]
										(if (= compareable-tile 0)
											(let []
												(if (= @steps 1) (rf/dispatch [:apply-tile-change (row x) (row (- @steps 1)) this-tile-val]))
												(swap! steps dec))
											(let []
												;;(println (:new (tiles (row (- @steps 1)))))
												(if (and (= compareable-tile this-tile-val)
														 (nil? (:new (tiles (row (- @steps 1))))))
													(rf/dispatch [:apply-tile-change (row x) (row (- @steps 1)) (+ this-tile-val 1)])
													(rf/dispatch [:apply-tile-change (row x) (row @steps) this-tile-val]))
												(reset! steps 0))))))))))
		  tiles)
		  ))

(rf/reg-event-db
  :add-tile
  (fn [db [_]]
  	(assoc-in db [:tiles (rand-tile db) :val] (if (> (rand 1) 0.9) 2 1))))

(rf/reg-event-db
  :keydown
  (fn [db [_ direction]]
  	(if (not= direction "false")
  		(let [tiles (get-in db [:tiles])]
			(assoc-in db [:tiles] (reduce (fn [tiles [index value]]
                										(assoc tiles index (dissoc value :key)))
                									  {}
                									  tiles))
			(move-tiles db direction)
			(assoc-in db [:tiles (rand-tile db) :val] (if (> (rand 1) 0.9) 2 1))))
		))

(rf/reg-event-db
  :catch-key
  (set! (.-onkeydown js/document) (fn [e]
  	(let [key (.-keyCode e)
		  direction (if (contains? #{38, 75, 87} key) 0
				    (if (contains? #{39, 76, 68} key) 1
				    (if (contains? #{40, 74, 83} key) 2
				    (if (contains? #{37, 72, 65} key) 3 "false"))))]
		(rf/dispatch [:keydown direction])))))

(rf/reg-event-db
  :reset-game
  (fn [db [_]]
  	(let [tiles (get-in db [:tiles])]
	  (->
	  	(assoc-in db [:tiles] (reduce (fn [tiles [index value]]
										(assoc tiles index (assoc value :val 0)))
									  {}
									  tiles))
		(assoc-in [:tiles (rand-tile db) :val] (if (> (rand 1) 0.9) 2 1))
		(assoc-in [:tiles (rand-tile db) :val] (if (> (rand 1) 0.9) 2 1))
		))))