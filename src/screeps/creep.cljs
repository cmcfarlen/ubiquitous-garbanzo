(ns screeps.creep
  (:refer-clojure :exclude [name]))

(defn body
  [c]
  (js->clj (.-body c)))

(defn name
  [c]
  (.-name c))

(defn move
  [c direction]
  (.move c direction))

(defn move-to
  [c target]
  (.moveTo c target))

