(ns clj-number-game-third.handlers
	(:require [re-frame.core :as rf]
			  [clj_number_game_third.db :as db]
			  [clj_number_game_third.subs :as sb]))

(rf/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(defn rand-tile [db]
	(let [empty-tiles- (filter #(= (:val (second %)) 0) (get-in db [:tiles]))]
			(if (not-empty empty-tiles-)
				(first (rand-nth empty-tiles-))
				0)))

(rf/reg-event-db
  :add-tile
  (fn [db [_]]
  	(assoc-in db [:tiles (rand-tile db) :val] (if (> (rand 1) 0.9) 2 1))))

(rf/reg-event-db
  :keydown
  (fn [db [_ direction]]
  	(let [tiles (get-in db [:tiles])]
  		(assoc-in db [:game-state] :processing-input)
		(rf/dispatch [:add-tile])
		(assoc-in db [:game-state] :waiting-input))))

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
		(assoc-in [:game-state] :waiting-input)))))

(rf/reg-event-db
  :game-won
  (fn [db _]
  	(assoc-in db [:game-state] :won)))

(rf/reg-event-db
  :game-lost
  (fn [db _]
  	(assoc-in db [:game-state] :lost)))