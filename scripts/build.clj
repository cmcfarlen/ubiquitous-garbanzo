(require '[cljs.build.api :as b])
(require '[clj-http.client :as client])

(println "Building ...")

(defn deploy-build
  []
  (let [user (System/getenv "SCREEPS_USERNAME")
        passwd (System/getenv "SCREEPS_PASSWORD")
        branch (or (System/getenv "SCREEPS_BRANCH") "test")]
    (if (and user passwd)
      (do
       (println "deploying code...")
       (client/post "https://screeps.com/api/user/code"
                    {:basic-auth [user passwd]
                     :content-type :json
                     :form-params {:branch branch
                                   :modules {:main (slurp "release/wtf.js")}}})
       (println "done!"))
      (println "export SCREEPS_USERNAME and SCREEPS_PASSWORD (optionally SCREEPS_BRANCH) to autodeploy code."))))

(let [start (System/nanoTime)]
  (b/watch "src"
    {:output-to "release/wtf.js"
     :output-dir "release"
     :optimizations :advanced
     :watch-fn deploy-build
     :verbose true})
  (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))
