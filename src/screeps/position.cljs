(ns screeps.position)

(defn create
  [x y room]
  (js/RoomPosition. x y room))

(defn position
  "return the position of the object or the object if it is a position.  nil if it can't get position"
  [o]
  (if (.hasOwnProperty o "pos")
    (aget o "pos")
    (if (.hasOwnProperty o "x")
      o)))

(defn range-to
  ([from x y]
   (.getRangeTo (position from) x y))
  ([from to]
   (.getRangeTo (position from) to)))

(defn in-range?
  ([p x y rng]
   (.inRangeTo (position p) x y rng))
  ([p o rng]
   (.inRangeTo (position p) o rng)))

(defn next-to?
  ([p x y]
   (.isNearTo (position p) x y))
  ([p o]
   (.isNearTo (position p) o)))

