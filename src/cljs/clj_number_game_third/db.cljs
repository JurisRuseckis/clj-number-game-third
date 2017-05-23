(ns clj-number-game-third.db
	(:require [re-frame.db :refer [app-db]]
			  [re-frame.core :as rf]
			  [reagent.ratom :as ra :include-macros true]))

(def default-db
	{:name				"clj_number_game_third"
	 :game-state				:init
	 :tiles				{ 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16}
	 :rows				[[(1,1) (2,1) (3,1) (4,1)]
	 					 [(1,2) (2,2) (3,2) (4,2)]
	 					 [(1,3) (2,3) (3,3) (4,3)]
	 					 [(1,4) (2,4) (3,4) (4,4)]]})