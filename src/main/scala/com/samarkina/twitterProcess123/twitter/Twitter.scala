package com.samarkina.twitterProcess123.twitter

import twitter4j.conf.ConfigurationBuilder

import scala.io.Source

object Twitter {
  def keys() = {

    val consumerKeyFile = Source.fromFile("src/main/resources/consumerKey.txt")
    val consumerKey = try consumerKeyFile.mkString finally consumerKeyFile.close()

    val consumerSecretFile = Source.fromFile("src/main/resources/consumerSecret.txt")
    val consumerSecret = try consumerSecretFile.mkString finally consumerSecretFile.close()

    val accessTokenFile = Source.fromFile("src/main/resources/accessToken.txt")
    val accessToken = try accessTokenFile.mkString finally accessTokenFile.close()

    val accessTokenSecretFile = Source.fromFile("src/main/resources/accessTokenSecret.txt")
    val accessTokenSecret = try accessTokenSecretFile.mkString finally accessTokenSecretFile.close()

//    println(consumerKey)
//    println(consumerSecret)
//    println(accessToken)
//    println(accessTokenSecret)

    val cb = new ConfigurationBuilder

    cb.setDebugEnabled(true)
      .setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecret)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret)

  }



}
