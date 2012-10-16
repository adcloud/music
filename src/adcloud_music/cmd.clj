(ns adcloud-music.cmd
    (:import [java.io BufferedReader InputStreamReader]))

(defn cmd [p] (.. Runtime getRuntime (exec (str p)))) 

(defn cmdout [o f] 
  (let [r (BufferedReader. 
          (InputStreamReader. 
          (.getInputStream o)))] 
    (dorun (map f (line-seq r))))) 

