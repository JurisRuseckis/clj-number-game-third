(ns clj-number-game-third.views
  (:require [re-frame.core :as rf]
            [reagent.core :as ra]
            [clj_number_game_third.subs :as sb]
            [clojure.string :as string]))

(defn exp2 [n]
     (if (zero? n) 1
         (* 2 (exp2 (dec n)))))
;; atoms
(defn title []
	[:h1.title "2048"])

(defn game-intro []
	[:p.game-intro "Join the numbers and get to the "
		[:strong] "2048 tile!"])

(defn restart-button []
	[:a.restart-button {:on-click #(rf/dispatch [:reset-game])}
	 "New game"])

(defn score []
	(let [score- (sb/get-score-)]
	[:div.score-container @score-] ))

(defn best-score []
	(let [best-score- (sb/get-best-score-)]
	 	 [:div.best-container @best-score-] ))

(defn grid-cell []
	[:div.grid-cell])

(defn tile [id]
	( let [tile-map- (sb/get-tile- id)
		   game-state- (sb/game-state-)
		   x (:x @tile-map-)
		   y (:y @tile-map-)
		   val (:val @tile-map-)]
	[:div {:class (string/join " "
					["tile"
					 (string/join "-" ["tile" val])
					 (if (= :processing-input @game-state-)
					 	(string/join "-" ["tile-position" 1 1 ])
					 	(string/join "-" ["tile-position" x y ]))])}
		(when (> val 0)
			[:div.tile-inner (exp2 val)])
			[:span {:style {:font-size 10}} @game-state-]]))

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
			(for [x (range 1 17)]
			 ^{:key x}
			  [tile x])]])

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