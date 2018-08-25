package com.github.mstump.gatling.kafka.request.builder

import com.github.mstump.gatling.kafka.action.KafkaProducerRequestActionBuilder
import io.gatling.core.session._

case class KafkaAttributes[K,V]( requestName: Expression[String],
                                 key: Option[Expression[K]],
                                 payload: Expression[V] )

case class KafkaRequestBuilder(requestName: Expression[String]) {

  def receive[K, V](): KafkaProducerRequestActionBuilder[K, V] = receive()

  def send[V](payload: Expression[V]): KafkaProducerRequestActionBuilder[_,V] = send(payload, None)

  def send[K,V](key: Expression[K], payload: Expression[V]): KafkaProducerRequestActionBuilder[K,V] = send(payload, Some(key))

  private def send[K,V](payload: Expression[V], key: Option[Expression[K]]) =
    new KafkaProducerRequestActionBuilder(KafkaAttributes(requestName, key, payload))

}
