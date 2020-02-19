package com.samarkina.twitterProcess123
import twitter.Twitter._
import spark.PrintTweets._
import spark.TwitterStreaming._

object First {

  def main(args: Array[String]): Unit = {

    println("Hello World!")

    getTwitts()
  }

}