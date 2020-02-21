package com.samarkina.twitterProcess123.kafka

import com.samarkina.twitterProcess123.spark.TwitterStreaming._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization._
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.internal.Logging
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.JavaConversions._

object Producer extends Logging {
  def main(args: Array[String]): Unit = {
    val appName = "Producer"
    val conf = new SparkConf()
    conf.setAppName(appName).setMaster("local[10]")


    val ssc = new StreamingContext(conf, Seconds(5))
    setStreamingLogLevels()

    val tweets = getTwitts(ssc)

    val cfg = Map(
      "bootstrap.servers" -> "localhost:9092",
      "key.serializer" -> classOf[StringSerializer],
      "value.serializer" -> classOf[StringSerializer])
    val producer = new KafkaProducer[String, String](cfg)

    tweets.foreachRDD { rdd =>
      rdd.collect().foreach {
        str =>
          val msg = new ProducerRecord[String, String]("topicA", null, str)
          producer.send(msg)
      }
    }


    ssc.start()
    ssc.awaitTermination()
  }

  def setStreamingLogLevels(): Unit = {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      // We first log something to initialize Spark's default logging, then we override the
      // logging level.
      logInfo("Setting log level to [WARN] for streaming example." +
        " To override add a custom log4j.properties to the classpath.")
      Logger.getRootLogger.setLevel(Level.WARN)
    }
  }

}