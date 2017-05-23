(ns clj-number-game-third.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]
            [re-frame.db :refer [app-db]]
            [reagent.ratom :as ra]))

;; get game state
(defn- game-state [db]
  (get-in db [:game-state]))
(defn game-state- []
  (reaction (game-state @app-db)))

;;get tile
(defn- get-tile [db id]
  (get-in db [:tiles id]))
(defn get-tile- [id]
  (reaction (get-tile @app-db id)))

;; how to get param of something
(defn- get-tile-x [db id]
  (:x (get-tile db id)))
(defn get-tile-x- [id]
  (reaction (get-tile-x @app-db id)))

;; get score
(defn- get-score [db]
  (get-in db [:score]))
(defn get-score- []
  (reaction (get-score @app-db)))

;; get best score
(defn- get-best-score [db]
  (get-in db [:best-score]))
(defn get-best-score- []
  (reaction (get-best-score @app-db)))