(ns adcloud-music.sound
    (:use [overtone.sc.node :only [ctl]]
          [overtone.core :only [connect-external-server]]))

(connect-external-server 57110)

(use 'adcloud-music.synth)
(use 'adcloud-music.dubstep)
(use 'adcloud-music.cmd)
(use 'adcloud-music.instruments)

(def tail-cmd "tail -f -n 0 ./events.log")
(def clicks [cash1 cash2 cash3 cash4 cash5 cash6 cash7])
(def conversions [applause1 applause2 applause3 applause4 applause5])

(defn click-sound [] ((rand-nth clicks)))
(defn conversion-sound [] ((rand-nth conversions)))

(defn line-type [line] (nth (re-find #"\"type\"\:\"(\w+)\"" line) 1))
(defn topic-id [line] (nth (re-find #"\"topic_id\"\:\"(\d+)\"" line) 1))
(defn ad-position [line] (nth (re-find #"\"ad_position\"\:\"?(\d+)\"?" line) 1))

(defn cash-sound [line]
  (if (.equals "click" (line-type line))
    (click-sound))
  (if (.equals "conversion" (line-type line))
    (conversion-sound)))

(defn adcloud-cash [] (cmdout (cmd tail-cmd) cash-sound))

(defn adcloud-music []
  (do

    (let [ds (dubstep 220)
          db (dub-bass)]

      (do
        (println "dubstep" (get ds :id))
        (println "dub-bass" (get db :id))

        (defn change [instr attr value]
          (ctl (get instr :id) attr value))

        (change ds :bpm 220)

        (defn engine [line]
          (if (.equals "click" (line-type line))
            (do 
              (play-random-chord)
              (change ds :note (rand-nth notes))
              (change ds :wobble (* 4 (Integer. (ad-position line))))))
          (if (.equals "retargeting" (line-type line))
            (if (== 1 (rand-nth (range 0 3000)))
              (change db :note (* 2 (rand-nth notes)))))
          (if (.equals "conversion" (line-type line))
            (conversion-sound)))

        (cmdout (cmd tail-cmd) engine)))))

