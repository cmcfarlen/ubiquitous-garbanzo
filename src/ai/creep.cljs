(ns ai.creep
  (:require [screeps.game :as game]
            [screeps.creep :as creep]
            [screeps.room :as room]
            [screeps.spawn :as spawn]
            [screeps.position :as pos]
            [screeps.structure :as structure]
            [screeps.memory :as m]))

(defn perform-at
  [creep tgt f]
  (if (pos/next-to? creep tgt)
    (f creep tgt)
    (creep/move-to creep tgt)))

(defn collect-energy
  [creep]
  (let [sim-room (game/rooms "sim")
        sources (room/find sim-room js/FIND_SOURCES_ACTIVE)
        source (nth sources (mod (.substring (creep/id creep) 2) (count sources)))
        ctrlr (room/controller sim-room)
        const-site (first (room/find sim-room js/FIND_CONSTRUCTION_SITES))
        empty-extension (first (room/find sim-room js/FIND_MY_STRUCTURES #(and
                                                                             (= (structure/type %) js/STRUCTURE_EXTENSION)
                                                                             (< (structure/energy %) (structure/energy-capacity %)))))
        m (creep/memory creep)
        sp1 (game/spawns "Spawn1")]

    (if (:dump m)
      (do
       (cond
        (and (not (nil? ctrlr)) (not (structure/mine? ctrlr)))
        (perform-at creep ctrlr creep/claim-controller)

        (= 1 (structure/level ctrlr))
        (perform-at creep ctrlr creep/upgrade-controller)

        const-site
        (perform-at creep const-site creep/build)

        (< (.-energy sp1) 300)
        (perform-at creep sp1 creep/transfer-energy)

        empty-extension
        (perform-at creep empty-extension creep/transfer-energy)

        :else
        (perform-at creep ctrlr creep/upgrade-controller))
       (if (= (creep/energy creep) 0)
         (creep/memory! creep (assoc m :dump false))))
      (if (= (creep/energy creep) (creep/energy-capacity creep))
        (creep/memory! creep (assoc m :dump true))
        (if (pos/next-to? creep source)
          (creep/harvest creep source)
          (creep/move-to creep source))))))
