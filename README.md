music
=====

generate some music from data.

howto
=====

this project works with data of some of our internal systems. so please feel free to play around with this code, but don't expect to get more than the underlying beat.

i got the best results using an external supercollider server. this is how i set this up.

* clone this repo
* log some real-time data from our adservers in this file: ./events.log (i've used scorpions)
* start the supercollider server

    sclang
    s.boot

* start the repl

    lein repl
    (sound/adcloud-music)

loading all the samples from freesound takes a while on the first run, but they are cached. you probably hit the repl timeout. just remove main in project.clj.
