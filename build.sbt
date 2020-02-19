name := "TwitterProcess123"

version := "0.1"

scalaVersion := "2.11.12"

val sparkVersion = "2.3.1"

// Spark
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core",
  "org.apache.spark" %% "spark-streaming",
  "org.apache.spark" %% "spark-sql"
).map(_ % sparkVersion)

// Twiiter
libraryDependencies ++= Seq(
  "org.apache.bahir" %% "spark-streaming-twitter"
).map(_ % sparkVersion)

// Kafka
libraryDependencies ++= Seq(
  "org.apache.kafka" %% "kafka",
  //  "org.apache.kafka" %% "kafka-streams-scala",
  "org.apache.spark" %% "spark-streaming-kafka-0-10"
).map(_ % sparkVersion)