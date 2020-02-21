#!/usr/bin/env bash

cd /usr/local/Cellar/kafka/2.4.0/libexec
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic topicA