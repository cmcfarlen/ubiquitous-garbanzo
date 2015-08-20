(ns screeps.core)

(defprotocol IGame
  (creeps [g] [g creep-name])
  (spawns [g] [g spawn-name])
  (rooms [g])
  )

(defprotocol ISpawn
  (create-creep [sp body creep-name] [sp body creep-name init-mem]))

(defprotocol ICreep
  (move [cr dir]))

(def kw->dir
  {:up js/TOP
   :down js/BOTTOM
   :left js/LEFT
   :right js/RIGHT
   :up-right js/TOP_RIGHT
   :down-right js/BOTTOM_RIGHT
   :up-left js/TOP_LEFT
   :down-left js/BOTTOM_LEFT})

(deftype Game [g]
 IGame
 (creeps [_]
   (.-creeps g))
 (creeps [_ creep-name]
   (if-let [c (aget (.-creeps g) creep-name)]
     (reify
       ICreep
       (move [cr dir]
         (.move c (kw->dir dir))))))
 (spawns [_]
   (.-spawns g))
 (spawns [_ spawn-name]
   (let [sp (aget (.-spawns g) spawn-name)]
     (reify
       ISpawn
       (create-creep [this body creep-name init-mem]
         (.createCreep sp (clj->js body) creep-name init-mem))
       (create-creep [this body creep-name]
         (create-creep this body creep-name nil)))))
 (rooms [_]
   (.-rooms g)))

(defn creep-count
  [cr]
  (count (js-keys cr)))

