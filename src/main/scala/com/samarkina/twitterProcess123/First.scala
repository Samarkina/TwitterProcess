package com.samarkina.twitterProcess123
import com.samarkina.twitterProcess123.spark.TwitterStreaming._
import org.apache.log4j.{Level, Logger}
import org.apache.spark.internal.Logging

object First extends Logging {

  def main(args: Array[String]): Unit = {

    println("Hello World!")

    setStreamingLogLevels()
    getTwitts()
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