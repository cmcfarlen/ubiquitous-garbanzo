(ns hello-world.core
  (:require [screeps.game :as game]
            [screeps.creep :as creep]
            [screeps.spawn :as spawn]
            [screeps.position :as pos]
            [screeps.memory :as m]))

(defn test-game
  []
  (.log js/console "time: " (game/time))
  (.log js/console "creeps: " (mapv creep/name (game/creeps)))
  (let [id (-> (game/creeps) first (.-id))]
    (.log js/console "object " id " - " (game/object id))
    )

  (.log js/console "used-cpu: " (game/used-cpu)))

(defn test-creep
  [c]
  (when c
    (.log js/console "creep name" (creep/name c))
    (let [sp1 (game/spawns "Spawn1")
          sp2 (game/spawns "Spawn2")]
      (if (pos/next-to? c sp1)
        (creep/move-to c sp2))
      (if (pos/next-to? c sp2)
        (creep/move-to c sp1)))
    (.log js/console "position p: " (pos/position c))))

(defn test-position
  []
  (.log js/console "type RoomPosition: " (type js/RoomPosition))
  (let [p (pos/create 5 5 "sim")]
    (.log js/console "position " (js->clj p) " type " (type p))
    )
  )

(defn test-memory
  []
  (let [t {:a 1 :b "two"}]
    (m/store :test t))

  (m/update :inc (fnil inc 0))
  (.log js/console "memory: " (m/memory :test))
  (.log js/console "memory: " (m/memory :inc))
  )

#_(test-game)

#_(test-creep (-> (game/creeps) first))

#_(test-position)
#_(test-memory)

(let [cnt (count (game/creeps))]
  (.log js/console (str "creeps --: " cnt))
  #_(.log js/console (mapv #(.-name %) (vals (js->clj (s/rooms g)))))
  (when (< cnt 5)
    (.log js/console "need more creeps!")
    (let [status (spawn/create-creep (game/spawns "Spawn1") [js/WORK js/CARRY js/MOVE])]
      (.log js/console (str "spawn returned: " status))))
  (.log js/console "moving creeps!!!!")
  (doseq [c (game/creeps)]
    (creep/move c (rand-nth [js/TOP js/BOTTOM js/LEFT js/RIGHT js/TOP_LEFT js/TOP_RIGHT js/BOTTOM_LEFT js/BOTTOM_RIGHT])))
  )

