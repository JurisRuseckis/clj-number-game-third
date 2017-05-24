(ns clj-number-game-third.db
	(:require [re-frame.db :refer [app-db]]
			  [re-frame.core :as rf]
			  [reagent.ratom :as ra :include-macros true]))

(def default-db
	{:name				"clj_number_game_third"
	 :game-state		:waiting-input
	 :score				0
	 :best-score		0
	 :empty-tiles		[1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16]
	 :tiles				{1	{:x 1 :y 1 :val nil}
						 2	{:x 2 :y 1 :val nil}
						 3	{:x 3 :y 1 :val nil}
						 4	{:x 4 :y 1 :val nil}
	 					 5	{:x 1 :y 2 :val nil}
	 					 6	{:x 2 :y 2 :val nil}
	 					 7	{:x 3 :y 2 :val nil}
	 					 8	{:x 4 :y 2 :val nil}
	 					 9	{:x 1 :y 3 :val nil}
	 					 10	{:x 2 :y 3 :val nil}
	 					 11	{:x 3 :y 3 :val nil}
	 					 12	{:x 4 :y 3 :val nil}
	 					 13	{:x 1 :y 4 :val nil}
	 					 14	{:x 2 :y 4 :val nil}
	 					 15	{:x 3 :y 4 :val nil}
	 					 16	{:x 4 :y 4 :val nil}}
	 :rows				[[1 2 3 4]
	 					 [5 6 7 8]
	 					 [9 10 11 12]
	 					 [13 14 15 16]

	 					 [1 5 9 13]
	 					 [2 6 10 14]
	 					 [3 7 11 15]
	 					 [4 8 12 16]]})