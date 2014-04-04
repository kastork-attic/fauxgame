package edu.nps.faux

import com.budjb.rabbitmq.MessageContext

class LobbyQueueListenerConsumer {
  /**
   * Consumer configuration.
   */
  static rabbitConfig = [
      "queue":"faux.queue"
  ]

  /**
   * Handle an incoming RabbitMQ message.
   *
   * @param body The converted body of the incoming message.
   * @param context Properties of the incoming message.
   * @return
   */
  def handleMessage(def body, MessageContext context) {
    // TODO: Handle Message.
  }

  def handleMessage(Map body, MessageContext context) {
    println "Fauxgame hears: $body, Context $context"
    println "      reply-to: ${context.properties.replyTo}"
  }
}
