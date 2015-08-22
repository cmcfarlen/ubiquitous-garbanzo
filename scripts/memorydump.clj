(require '[clojure.data.codec.base64 :as b64]
         '[clojure.java.io :as io]
         '[cheshire.core :as json]
         '[clj-http.client :as client])

(import '[java.io ByteArrayInputStream])
(import '[java.util.zip GZIPInputStream])

(defn gunzip-stream
  [ba]
  (-> ba
      (ByteArrayInputStream.)
      (GZIPInputStream.)))

(defn dump-memory
  [user passwd]
  (if (and user passwd)
    (do
     (let [resp (client/get "https://screeps.com/api/user/memory"
                            {:basic-auth [user passwd]
                             :content-type :json
                             :as :json})]
       (-> (-> resp :body :data)
           (.substring 3)
           (.getBytes)
           (b64/decode)
           (gunzip-stream)
           (slurp)
           (json/parse-string))))))

(let [user (System/getenv "SCREEPS_USERNAME")
      passwd (System/getenv "SCREEPS_PASSWORD")]
  (dump-memory user passwd))


