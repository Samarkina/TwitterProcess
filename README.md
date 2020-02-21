# Twitter Process

1. Broker - it is zookeeper and kafka in console (Topic needs to be created in broker)
if you don't have the topics, then you can create it (scripts/createTopicKafka.sh)
run scripts:

    a. scripts/runKafka.sh

    b. scripts/runZookeeper.sh

    (c.) You can see data in Kafka console (scripts/watchKafkaConsole.sh)

2. Run Kafka Consumer (first main)

    (src/main/scala/com/samarkina/twitterProcess123/kafka/Consumer)

3. Run Kafka Producer (second main, there will be text)

    (src/main/scala/com/samarkina/twitterProcess123/kafka/Producer)