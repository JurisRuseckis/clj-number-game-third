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

(defn score [& score]
	(into [:div.score-container
		{:default-value "0"}] score))

(defn best-score [& best-score]
	(into [:div.best-container
		{:default-value "0"}] best-score))

(defn game-cell [id]
	[:div.grid-cell])

;; molecules

(defn score-board [& contents]
	(into [:div.scores-container] contents))

(defn game-row [id]
	[:div.grid-row
		(for [n (range 1 5)]
			  ^{:key n}
			  [game-cell n])])

;; organisms
(defn header [& contents]
	(into [:div.heading] contents))

(defn above-game [& contents]
	(into [:div.above-game] contents))

(defn game-container []
	[:div.game-container
		(for [n (range 1 5)]
              ^{:key n}
              [game-row n])])

(defn copyrights []
	[:div.game-explanation
		[:p "This is not original game. Original game is avaiable here: "
			[:a {:href "http://gabrielecirulli.com" :target "_blank"} "http://gabrielecirulli.com"]]])

;;container
(defn container []
	[:div.container
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