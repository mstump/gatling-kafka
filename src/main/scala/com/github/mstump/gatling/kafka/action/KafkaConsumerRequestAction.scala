package com.github.mstump.gatling.kafka.action

import com.github.mstump.gatling.kafka.protocol.KafkaProtocol
import com.github.mstump.gatling.kafka.request.builder.KafkaAttributes

import io.gatling.core.action.{ChainableAction, Action}
import io.gatling.commons.stats.{KO, OK}
import io.gatling.core.session._
import io.gatling.commons.util.ClockSingleton._
import io.gatling.commons.validation.Validation
import io.gatling.core.CoreComponents
import io.gatling.core.util.NameGen
import io.gatling.core.stats.message.ResponseTimings

import org.apache.kafka.clients.consumer._


class KafkaConsumerRequestAction[K, V](val consumer: KafkaConsumer[K, V],
                                       val kafkaAttributes: KafkaAttributes[K, V],
                                       val coreComponents: CoreComponents,
                                       val kafkaProtocol: KafkaProtocol,
                                       val throttled: Boolean,
                                       val next: Action)
  extends ChainableAction with NameGen {

  val statsEngine = coreComponents.statsEngine
  override val name = genName("kafkaConsumerRequest")

  override def execute(session: Session) {
    val outcome = consumer.poll(2000)
  }

}
