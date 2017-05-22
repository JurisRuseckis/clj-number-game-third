(ns clj-number-game-third.db
	(:require [re-frame.db :refer [app-db]]
			  [re-frame.core :as rf]
			  [reagent.ratom :as ra :include-macros true]))

(def default-db
	{:name "clj_number_game_third"
	 :tiles { 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16}
	 :rows [[1  2  3  4 ]
	 		[5  6  7  8 ]
	 		[9  10 11 12]
	 		[13 14 15 16]]})