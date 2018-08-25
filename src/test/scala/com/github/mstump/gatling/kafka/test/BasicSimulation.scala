package com.github.mstump.gatling.kafka.test

import io.gatling.core.Predef._
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.consumer.ConsumerConfig
import scala.concurrent.duration._

import com.github.mstump.gatling.kafka.Predef._

class BasicSimulation extends Simulation {
  val kafkaConf = kafka
    // Kafka topic name
    .topic("test")
    // Kafka producer configs
    .properties(
    Map(
      ProducerConfig.ACKS_CONFIG -> "1",
      // list of Kafka broker hostname and port pairs
      ProducerConfig.BOOTSTRAP_SERVERS_CONFIG -> "localhost:9092",

      // in most cases, StringSerializer or ByteArraySerializer
      ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG ->
        "org.apache.kafka.common.serialization.StringSerializer",
      ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG ->
        "org.apache.kafka.common.serialization.StringSerializer",

      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "localhost:9092",
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG ->
        "org.apache.kafka.common.serialization.StringSerializer",
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG ->
        "org.apache.kafka.common.serialization.StringSerializer",
    )
  )

  val producer = scenario("Kafka Test Producer")
    .exec(
      kafka("producer")
        // message to send
        .send[String]("foo")
    )

  val consumer = scenario("Kafka Test Consumer")
    .exec(
      kafka("consumer")
        .receive[String, String]()
    )

  setUp(
    producer
      .inject(constantUsersPerSec(10) during (90 seconds)),
    consumer
      .inject(constantUsersPerSec(10) during (90 seconds))
  ).protocols(kafkaConf)
}
