(def channelWrite (a/chan 10))
(def channelRead1 (a/chan 10))
(def channelRead2 (a/chan 10))
(def channelRead3 (a/chan 10))

(>!! channelRead1 "hello")
(>!! channelRead2 "hello")
(>!! channelRead3 "hello")

(def setOfChannelsRead [channelRead1 channelRead2 channelRead3])

(defn writeToChannel [chanW setOfChanR]
    (def setOfMessages (map <!! setOfChanR))
    (if (apply = setOfMessages)
            (>!! chanW (first setOfMessages))
            (println "The messages are different!")
        )
    )

(writeToChannel channelWrite setOfChannelsRead)

(take! channelWrite println)
