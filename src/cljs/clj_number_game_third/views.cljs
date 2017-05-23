(ns clj-number-game-third.views
  (:require [re-frame.core :as rf]
            [reagent.core :as ra]
            [clj_number_game_third.subs :as sb]
            [clojure.string :as string]))

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

(defn tile [x y val]
	[:div {:class (string/join " " ["tile" (string/join "-" ["tile" val]) (string/join "-" ["tile-position" x y ])])}
		[:div.tile-inner val]])

;; molecules

(defn score-board [& content]
	(into [:div.scores-container] content))

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

(defn tile-container [& content]
	(into [:div.tile-container] content))

;; organisms
(defn header [& content]
	(into [:div.heading] content))

(defn above-game [& content]
	(into [:div.above-game] content))

(defn game-container []
	[:div.game-container
		[grid-container]
		[tile-container
			[tile 1 1 2]
			[tile 2 2 16]
			[tile 3 3 32]
			[tile 4 4 128]]])

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
