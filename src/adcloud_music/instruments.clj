(ns adcloud-music.instruments
    (:use [overtone.core]
          [overtone.inst.sampled-piano]))

(def cash1 (sample (freesound-path 91924)))
(def cash2 (sample (freesound-path 32634)))
(def cash3 (sample (freesound-path 75235)))
(def cash4 (sample (freesound-path 32629)))
(def cash5 (sample (freesound-path 32633)))
(def cash6 (sample (freesound-path 47962)))
(def cash7 (sample (freesound-path 32631)))

(def applause1 (sample (freesound-path 22952)))
(def applause2 (sample (freesound-path 18665)))
(def applause3 (sample (freesound-path 32865)))
(def applause4 (sample (freesound-path 99636)))
(def applause5 (sample (freesound-path 35036)))

(def notes (vec [25 27 28 35 40 41 50 78]))

(def piano sampled-piano)

(defn play-chord [a-chord]
    (doseq [note a-chord] (piano note)))

(def chords (vec [[:D3 :major7] [:A3 :major] [:G3 :major7] [:E3 :minor] [:F3 :major7]]))

(defn play-random-chord []
  (play-chord (apply chord (rand-nth chords))))

