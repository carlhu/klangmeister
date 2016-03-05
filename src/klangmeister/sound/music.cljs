(ns klangmeister.sound.music
  (:require [leipzig.temperament :as temperament]
            [klangmeister.sound.synthesis :as synthesis]))

(defn play! [audiocontext notes]
  (doseq [{:keys [time duration instrument] :as note} notes]
    (let [at (+ time (.-currentTime audiocontext))
          synth-instance (-> note
                             (update :pitch temperament/equal)
                             (dissoc :time)
                             instrument)
          connected-instance (synthesis/connect synth-instance synthesis/destination)]
      (connected-instance audiocontext at duration))))
