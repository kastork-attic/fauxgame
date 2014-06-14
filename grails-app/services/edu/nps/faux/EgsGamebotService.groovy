package edu.nps.faux

import com.budjb.rabbitmq.RabbitMessageBuilder
import com.budjb.requestbuilder.RequestBuilder
import com.budjb.requestbuilder.ResponseStatusException
import edu.nps.fauxgame.LobbyServer


class EgsGamebotService
{

  static int rpcCounter = 0

  def gameUpdateViaAMQP(int lobbyServerId, ArrayList updates)
  {
    EgsGamebotService.rpcCounter += 1

    def lobbyServer = LobbyServer.get(lobbyServerId)

    def message = [
        method : "game-updates",
        id     : EgsGamebotService.rpcCounter,
        jsonrpc: "2.0",
        params : [
            payload: [
                update: updates
            ]
        ]
    ]

    new RabbitMessageBuilder().send {
      routingKey = "lobby.update.queue"
      body = message
    }


    log.debug("gamebot data: ${updates}")
  }

  def gameUpdates(LobbyServer lobbyServer, ArrayList updates)
  {
    EgsGamebotService.rpcCounter += 1

    println "sending gamebot data: ${updates}"


    def postBody = [method : "game-updates",
                    id     : EgsGamebotService.rpcCounter,
                    jsonrpc: "2.0",
                    params : [
                        payload: [
                            update: updates
                        ]
                    ]

    ]

    def responseData = null

    try
    {
      responseData = new RequestBuilder().post {
        uri = lobbyServer.baseURL + lobbyServer.gameBot;
        useBasicAuth = true
        basicAuthUserName = lobbyServer.lobbyUsername
        basicAuthPassword = lobbyServer.lobbyPassword
        body = postBody
      }

      log.debug(responseData)

    } catch (ResponseStatusException ex)
    {
      log.debug("Game Update call to lobby returned ${ex.status}\n${ex.content}")
    }

    responseData

  }
}
