(ns hello-world.core
  (:require [screeps.game :as game]
            [screeps.creep :as creep]
            [screeps.room :as room]
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

(defn test-room
  []
  (let [sim-room (game/rooms "sim")
        spawns (room/find sim-room js/FIND_MY_SPAWNS)]
    (.log js/console "spawns: " spawns)
    (.log js/console "creeps: " (room/find sim-room js/FIND_CREEPS #(= "Abigail" (creep/name %))))
    (.log js/console "path: " (room/find-path sim-room (pos/create 5 5 "sim") (pos/create 10 10 "sim")))
    (.log js/console "look [5 5]: " (room/look sim-room 5 5))
    (.log js/console "look for 'terrain' [5 5]: " (room/look-for sim-room 5 5 "terrain"))
    (.log js/console "look for 'road' [5 5]: " (room/look-for sim-room 5 5 "terrain"))
    (room/create-construction-site sim-room 5 5 js/STRUCTURE_ROAD)
    (.log js/console "road const: " js/STRUCTURE_ROAD)

    )
  )

(defn collect-energy
  [creep]
  (let [sim-room (game/rooms "sim")
        sources (room/find sim-room js/FIND_SOURCES_ACTIVE)
        source (nth sources (mod (.substring (creep/id creep) 2) (count sources)))
        ctrlr (room/controller sim-room)
        const-site (first (room/find sim-room js/FIND_CONSTRUCTION_SITES))
        m (creep/memory creep)
        sp1 (game/spawns "Spawn1")]
    (if (:dump m)
      (do
       (cond
        (< (.-energy sp1) 300)
        (if (pos/next-to? creep sp1)
          (creep/transfer-energy creep sp1)
          (creep/move-to creep sp1)) 

        const-site
        (if (pos/next-to? creep const-site)
          (creep/build creep const-site)
          (creep/move-to creep const-site))

        :else
        (if (pos/next-to? creep ctrlr)
          (do
           (creep/claim-controller creep ctrlr)
           (creep/upgrade-controller creep ctrlr))
          (creep/move-to creep ctrlr)))
       (if (= (creep/energy creep) 0)
         (creep/memory! creep (assoc m :dump false))))
      (if (= (creep/energy creep) 50)
        (creep/memory! creep (assoc m :dump true))
        (if (pos/next-to? creep source)
          (creep/harvest creep source)
          (creep/move-to creep source))))))



#_(test-game)

#_(test-creep (-> (game/creeps) first))

#_(test-position)
#_(test-memory)

#_(test-room)

(let [cnt (count (game/creeps))]
  (.log js/console (str "creeps --: " cnt))
  #_(.log js/console (mapv #(.-name %) (vals (js->clj (s/rooms g)))))
  (when (< cnt 10)
    (.log js/console "need more creeps!")
    (let [status (spawn/create-creep (game/spawns "Spawn1") [js/WORK js/CARRY js/MOVE])]
      (.log js/console (str "spawn returned: " status)))))

(doseq [c (game/creeps)]
  (collect-energy c))

#_(.log js/console (str "" (game/used-cpu) "/" (game/cpu-limit)))

