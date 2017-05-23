(ns clj-number-game-third.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]
            [re-frame.db :refer [app-db]]
            [reagent.ratom :as ra]))

(defn- game-state [db]
  (get-in db [:game-state]))

(defn game-state- []
  (reaction (game-state @app-db)))
