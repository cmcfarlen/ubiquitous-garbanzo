(ns screeps.spawn
  (:refer-clojure :exclude [name]))

(defn name
  [s]
  (.-name s))

(defn create-creep
  [sp body]
  (.createCreep sp (clj->js body) nil nil))

