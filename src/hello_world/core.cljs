(ns hello-world.core
  (:require [screeps.core :as s]
            [screeps.game :as game]
            [screeps.creep :as creep]
            [screeps.position :as pos]
            [screeps.memory :as m]
            ))

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

(let [g (s/Game. js/Game)
      cnt (s/creep-count (s/creeps g))]
  (.log js/console (str "creeps --: " cnt))
  (.log js/console (mapv #(.-name %) (vals (js->clj (s/rooms g)))))
  (when (< cnt 5)
    (.log js/console "need more creeps!")
    (let [status (s/create-creep (s/spawns g "Spawn1") [js/WORK js/CARRY js/MOVE] nil)]
      (.log js/console (str "spawn returned: " status))))
  (.log js/console "moving creeps!!!!")
  (doseq [c (->>
             (js-keys (s/creeps g))
             (map #(s/creeps g %)))]
    (s/move c (rand-nth (keys s/kw->dir))))
  )

