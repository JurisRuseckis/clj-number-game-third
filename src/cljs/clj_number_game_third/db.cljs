(ns clj-number-game-third.db
	(:require [re-frame.db :refer [app-db]]
			  [re-frame.core :as rf]
			  [reagent.ratom :as ra :include-macros true]))

(def default-db
	{:name				"clj_number_game_third"
	 :game-state		:waiting-input
	 :score				0
	 :best-score		0
	 :tiles				{1	{:x 1 :y 1 :val 0}
						 2	{:x 2 :y 1 :val 0}
						 3	{:x 3 :y 1 :val 0}
						 4	{:x 4 :y 1 :val 0}
	 					 5	{:x 1 :y 2 :val 0}
	 					 6	{:x 2 :y 2 :val 0}
	 					 7	{:x 3 :y 2 :val 0}
	 					 8	{:x 4 :y 2 :val 0}
	 					 9	{:x 1 :y 3 :val 0}
	 					 10	{:x 2 :y 3 :val 0}
	 					 11	{:x 3 :y 3 :val 0}
	 					 12	{:x 4 :y 3 :val 0}
	 					 13	{:x 1 :y 4 :val 0}
	 					 14	{:x 2 :y 4 :val 0}
	 					 15	{:x 3 :y 4 :val 0}
	 					 16	{:x 4 :y 4 :val 0}}
	 :rows				{0 [[1 5 9 13]
							[2 6 10 14]
							[3 7 11 15]
							[4 8 12 16]]
	 					 1 [[4 3 2 1]
							[8 7 6 5]
							[12 11 10 9]
							[16 15 14 13]]
						 2 [[13 9 5 1]
							[14 10 6 2]
							[15 11 7 3]
							[16 12 8 4]]
						 3 [[1 2 3 4]
							[5 6 7 8]
							[9 10 11 12]
							[13 14 15 16]]}})
