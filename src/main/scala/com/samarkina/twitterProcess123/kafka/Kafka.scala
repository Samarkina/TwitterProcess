package com.samarkina.twitterProcess123.kafka

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.DStream

import collection.JavaConversions._
import org.apache.kafka.common.serialization._
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.spark.SparkConf

object Kafka {
  def main(args: Array[String]): Unit = {

    val appName = "Consumer"
    val conf = new SparkConf()
    conf.setAppName(appName).setMaster("local[10]")
    val ssc = new StreamingContext(conf, Seconds(5))

    val messages = kafkaConsumer(ssc)
    messages.print()

    ssc.start()
    ssc.awaitTermination()

  }

  def kafkaConsumer(ssc: StreamingContext): DStream[(String, String)] = {
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    ssc.sparkContext.setLogLevel("WARN")

    val topics = Array("topicA")
    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    stream.map(record => (record.key, record.value))

  }

  def kafkaProducer(msg: ProducerRecord[Int, String]) = {

    val cfg = Map(
      "bootstrap.servers" -> "localhost:9092",
      "key.serializer" -> classOf[IntegerSerializer],
      "value.serializer" -> classOf[StringSerializer])
    val producer = new KafkaProducer[Int, String](cfg)

    val f = producer.send(msg)

    f.get

    producer.close
  }

}
