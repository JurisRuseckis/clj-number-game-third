(ns clj-number-game-third.core
	(:require [reagent.core :as reagent]
			  [re-frame.core :as re-frame]
			  [clj-number-game-third.handlers]
              [clj-number-game-third.subs]
              [clj-number-game-third.views :as views]))

(defn mount-root []
	(reagent/render [views/container]
		(.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
