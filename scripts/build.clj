(require '[cljs.build.api :as b])
(require '[clj-http.client :as client])

(println "Building ...")

(defn deploy-build
  []
  (println "deploying code...")
  (client/post "https://screeps.com/api/user/code"
               {:basic-auth ["email" "password"]
                :content-type :json
                :form-params {:branch "test"
                              :modules {:main (slurp "release/wtf.js")}}
                }
               )
  (println "done!")
  )

(let [start (System/nanoTime)]
  (b/watch "src"
    {:output-to "release/wtf.js"
     :output-dir "release"
     :optimizations :advanced
     :externs ["externs/screeps-externs.js"]
     :watch-fn deploy-build
     :verbose true})
  (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))
