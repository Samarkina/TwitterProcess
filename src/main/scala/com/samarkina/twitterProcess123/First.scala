package com.samarkina.twitterProcess123
import com.samarkina.twitterProcess123.spark.TwitterStreaming._
import com.samarkina.twitterProcess123.kafka.Kafka._
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.internal.Logging
import org.apache.spark.streaming.{Seconds, StreamingContext}

object First extends Logging {
  // second main with console text
  def main(args: Array[String]): Unit = {
//
//    val appName = "Producer"
//    val conf = new SparkConf()
//    conf.setAppName(appName).setMaster("local[10]")


//    val ssc = new StreamingContext(conf, Seconds(5))
    setStreamingLogLevels()

//    val tweets = getTwitts(ssc)
//    tweets.print()


    val msg = new ProducerRecord("topicA", 1, "hello")
    kafkaProducer(msg)


    // 1. Create ssc
    // 2. Run Kafka Consumer (second main)
    // 3. Create Kafka Producer (first main, there will be text in console)
    // 4. Broker - it is zookeeper and kafka in console
    // 5. Topic needs to be created in broker. Везде одинакоавые!

//    ssc.start()
//    ssc.awaitTermination()
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