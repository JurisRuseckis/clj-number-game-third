(ns clj-number-game-third.views
  (:require [re-frame.core :as rf]
            [reagent.core :as ra]
            [clj_number_game_third.subs :as sb]))

(defn title []
	[:h1.title "2048"])

(defn header [& contents]
	(into [:div.heading] contents))

(defn container []
	[:div.container
		[header
			[title]]])