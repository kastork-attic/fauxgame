package edu.nps.fauxgame

import com.budjb.rabbitmq.RabbitMessageBuilder
import com.budjb.requestbuilder.RequestBuilder
import com.budjb.requestbuilder.ResponseStatusException
import com.sun.jersey.api.client.ClientResponse
import grails.converters.JSON


class EgsProfileService
{

  def profileGetViaAMQP(String userEmail, String gameTitle, String gameVersion, String gameRole, String gameId)
  {
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
  def profileGet(LobbyServer lobbyServer, String userEmail, String gameTitle, String gameVersion, String gameRole, String gameId)
  {
    def reply = null

    try
    {
      def responseData = new RequestBuilder().get {
        uri = lobbyServer.baseURL + lobbyServer.profile
        useBasicAuth = true
        basicAuthUserName = lobbyServer.lobbyUsername
        basicAuthPassword = lobbyServer.lobbyPassword
        convertJson = false
        query = [
            email: userEmail,
            title: gameTitle,
            ver  : gameVersion,
            role : gameRole,
            gid  : gameId
        ]
      }

      log.debug(responseData.getClass())
      // The liferay web service returns a json object with 'text/javascript'
      // So just treat it as a string and parse it ourselves
      reply = JSON.parse(responseData)



    } catch (ResponseStatusException ex)
    {
      log.debug("Profile call to lobby returned ${ex.status}\n${ex.content}")
      reply = ex.content
    }

    reply
  }
}
