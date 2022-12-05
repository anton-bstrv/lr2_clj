(def channelWrite (chan 10))
(def channelRead1 (chan 10))
(def channelRead2 (chan 10))
(def channelRead3 (chan 10))
(def channelRead4 (chan 10))

(go (>! channelRead1 "hello"))
(go (>! channelRead2 "hello"))
(go (>! channelRead3 "hello"))
(go (>! channelRead4 "hello"))

(defn writeToChannel [chanWrite & setOfChanRead]
  (def setOfChan (into [] setOfChanRead))
  (go-loop [setOfMessages []]
             (if (> (count setOfChan) 0)
               (let [mess (<! (last setOfChan))]
                 (do
                   (def setOfChan (pop setOfChan))
                   (recur (conj setOfMessages mess)))
                 )
               (if (apply = setOfMessages)
                        (go(>! chanWrite (first setOfMessages)))
                        (println "The messages are different!")
                     )
               )
             )
    )

(writeToChannel channelWrite channelRead1 channelRead2 channelRead3 channelRead4)

(take! channelWrite println)
