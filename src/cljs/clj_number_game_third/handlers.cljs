(ns clj-number-game-third.handlers
	(:require [re-frame.core :as rf]
			  [clj_number_game_third.db :as db]
			  [clj_number_game_third.subs :as sb]))

(rf/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))