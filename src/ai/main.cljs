(ns ai.main
  (:require [screeps.game :as game]
            [screeps.creep :as creep]
            [screeps.room :as room]
            [screeps.spawn :as spawn]
            [screeps.position :as pos]
            [screeps.structure :as structure]
            [screeps.memory :as m]
            [ai.creep :as ai]))

(let [cnt (count (game/creeps))]
  (.log js/console (str "creeps --: " cnt))
  #_(.log js/console (mapv #(.-name %) (vals (js->clj (s/rooms g)))))
  (cond
   (< cnt 5)
   (spawn/create-creep (game/spawns "Spawn1") [js/WORK js/CARRY js/MOVE])

   (< cnt 20)
   (spawn/create-creep (game/spawns "Spawn1") [js/WORK js/CARRY js/CARRY js/MOVE js/MOVE])

   (< cnt 30)
   (spawn/create-creep (game/spawns "Spawn1") [js/WORK js/WORK js/CARRY js/CARRY js/CARRY js/MOVE]))
  #_(when (< cnt 20)
    (.log js/console "need more creeps!")
    (let [status (spawn/create-creep (game/spawns "Spawn1") [js/WORK js/CARRY js/MOVE])]
      (.log js/console (str "spawn returned: " status)))))

(doseq [c (game/creeps)]
  (ai/collect-energy c))

#_(.log js/console (str "" (game/used-cpu) "/" (game/cpu-limit)))

