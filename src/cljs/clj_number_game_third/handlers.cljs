(ns clj-number-game-third.handlers
	(:require [re-frame.core :as rf]
			  [clj_number_game_third.db :as db]
			  [clj_number_game_third.subs :as sb]))

(rf/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(defn get-direction [key]
	(if (contains? #{38, 75, 87} key) 0
	(if (contains? #{39, 76, 68} key) 1
	(if (contains? #{40, 74, 83} key) 2
	(if (contains? #{37, 72, 65} key) 3 "false")))))


(defn keydown [e]
  (println (.-keyCode e) (get-direction (.-keyCode e))))

(rf/reg-event-db
  :keydown
  (set! (.-onkeydown js/document) keydown))