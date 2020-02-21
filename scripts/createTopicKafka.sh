#!/usr/bin/env bash

cd /usr/local/Cellar/kafka/2.4.0/libexec
./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic topicA
./bin/kafka-topics.sh --list --bootstrap-server localhost:9092