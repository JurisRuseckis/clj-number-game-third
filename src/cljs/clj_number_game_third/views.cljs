(ns clj-number-game-third.views
  (:require [re-frame.core :as rf]
            [reagent.core :as ra]
            [clj_number_game_third.subs :as sb]))

;; atoms
(defn title []
	[:h1.title "2048"])

(defn game-intro []
	[:p.game-intro "Join the numbers and get to the "
		[:strong] "2048 tile!"])

(defn restart-button []
	[:a.restart-button "New game"])

(defn score [score]
	(into [:div.score-container
		{:default-value "0"}] score))

(defn best-score [best-score]
	(into [:div.best-container
		{:default-value "0"}] best-score))

(defn grid-cell []
	[:div.grid-cell])

;; molecules

(defn score-board [& contents]
	(into [:div.scores-container] contents))

(defn grid-row []
	[:div.grid-row
		(for [n (range 1 5)]
			  ^{:key n}
			  [grid-cell])])

(defn grid-container []
	[:div.grid-container
		(for [n (range 1 5)]
			  ^{:key n}
              [grid-row])])

(defn tile-container []
	[:div.tile-container ])

;; organisms
(defn header [& contents]
	(into [:div.heading] contents))

(defn above-game [& contents]
	(into [:div.above-game] contents))

(defn game-container []
	[:div.game-container
		[grid-container]
		[tile-container]])



(defn copyrights []
	[:div.game-explanation
		[:p "This is not original game. Original game is avaiable here: "
			[:a {:href "http://gabrielecirulli.com" :target "_blank"} "http://gabrielecirulli.com"]]])

;;container
(defn container []
	[:div.container
		[:input {:style {:display "none"}
				 :on-key-press #(println (.-charCode %))}]
		[header
			[title]
			[score-board
            	[score]
            	[best-score]]]
        [above-game
        	[game-intro]
        	[restart-button]]
        [game-container]
        [copyrights]])
