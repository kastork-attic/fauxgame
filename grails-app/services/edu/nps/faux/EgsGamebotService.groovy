package edu.nps.faux

import com.budjb.rabbitmq.RabbitMessageBuilder
import edu.nps.fauxgame.LobbyServer
import groovy.json.JsonBuilder
import groovyx.net.http.HTTPBuilder
import net.sf.json.JSONArray
import net.sf.json.JSONObject
import static groovyx.net.http.ContentType.JSON
import groovyx.net.http.HttpResponseDecorator


class EgsGamebotService
{

  static int rpcCounter = 0

  def gameUpdateViaAMQP(int lobbyServerId, ArrayList updates)
  {
    EgsGamebotService.rpcCounter += 1

    def lobbyServer = LobbyServer.get(lobbyServerId)

    def message = [
        method : "game-updates",
        id     : "${EgsGamebotService.rpcCounter}",
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

  def gameUpdates(int lobbyServerId, ArrayList updates)
  {
    EgsGamebotService.rpcCounter += 1

    def lobbyServer = LobbyServer.get(lobbyServerId)
    JSONObject responseData

    println "gamebot data: ${updates}"

    JsonBuilder builder = new JsonBuilder()

    def jsonBody = builder {
      method "game-updates"
      id "${EgsGamebotService.rpcCounter}"
      jsonrpc "2.0"
      params {
        payload {
          update updates
        }
      }

    }

    println "Built JSON string: ${builder.toPrettyString()}"
    println "Built JSON string: ${builder.toString()}"

    def http = new HTTPBuilder(lobbyServer.baseURL)

    println "Will authorize with $lobbyServer.lobbyUsername and $lobbyServer.lobbyPassword"

    http.auth.basic lobbyServer.lobbyUsername, lobbyServer.lobbyPassword

    def postBody = builder.toString()

//    def postBody = [method : "game-updates",
//                    id     : "${EgsGamebotService.rpcCounter}",
//                    jsonrpc: "2.0",
//                    params : [
//                        payload: [
//                            update: updates
//                        ]
//                    ]
//
//    ]


    println "content type will be: ${groovyx.net.http.ContentType.JSON}"

    http.post(path: "${lobbyServer.gameBot}", body: postBody, requestContentType: groovyx.net.http.ContentType.JSON) { HttpResponseDecorator resp, json ->
      println resp.status
      println resp.allHeaders
      println resp.contentType
      println resp.data
      println json.responseData
      responseData = json.responseData
    }


    responseData
  }
}
