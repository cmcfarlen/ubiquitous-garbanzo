(ns screeps.memory)

(defn memory
  ([]
   (js->clj js/Memory))
  ([k]
   (js->clj (aget js/Memory (name k)) :keywordize-keys true)))

(defn store
  [k o]
  (aset js/Memory (name k) (clj->js o)))

(defn update
  "call f with memory location k and store the result back in k"
  [k f]
  (let [d (memory k)]
    (store k (f d))))


