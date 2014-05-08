package edu.nps.fauxgame

import com.budjb.rabbitmq.RabbitMessageBuilder
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseDecorator
import net.sf.json.JSONObject


class EgsProfileService {

  def profileGetViaAMQP(String userEmail, String gameTitle, String gameVersion, String gameRole, String gameId) {
    def lobbyServer = LobbyServer.get(1)

    def message = [
        email: userEmail,
        title: gameTitle,
        ver  : gameVersion,
        role : gameRole,
        gid  : gameId
    ]

    def response = new RabbitMessageBuilder().rpc {
      routingKey = "lobby.query.queue"
      body = message
      replyTo = "faux.reply.queue"
      timeout = RabbitMessageBuilder.DEFAULT_TIMEOUT
    }

    println("Response to AMQP request:\n${response}")

    response
  }

  // https://globalecco.org/api/secure/jsonws/egs-portlet.gamingprofile/get?email=member@domain&title=someTitle&ver=xx&role&gid=xxxx
  def profileGet(String userEmail, String gameTitle, String gameVersion, String gameRole, String gameId) {
    def lobbyServer = LobbyServer.get(1)

    JSONObject responseData

    def http = new HTTPBuilder(lobbyServer.baseURL)
    http.auth.basic lobbyServer.lobbyUsername, lobbyServer.lobbyPassword

    println "Will GET: ${lobbyServer.profile}"

    def foo = http.get(path: "${lobbyServer.profile}",
        query: [email: userEmail,
                title: gameTitle,
                ver  : gameVersion,
                role : gameRole,
                gid  : gameId]
    ) { HttpResponseDecorator resp, json ->
      println "Returned response: ${resp.allHeaders}"
      responseData = json.responseData
    }

    println "Foo is $foo"

    responseData
  }
}
