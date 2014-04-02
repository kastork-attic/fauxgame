package edu.nps.faux

import edu.nps.fauxgame.LobbyServer
import groovy.json.JsonBuilder
import groovyx.net.http.HTTPBuilder
import net.sf.json.JSONArray
import net.sf.json.JSONObject
import static groovyx.net.http.ContentType.JSON

class EgsGamebotService {

  static int rpcCounter = 0

  def gameUpdates(int lobbyServerId, ArrayList updates) {

    EgsGamebotService.rpcCounter += 1

    def lobbyServer = LobbyServer.get(lobbyServerId)
    JSONObject responseData

    println "gamebot data: ${updates}"

    JsonBuilder builder = new JsonBuilder()

//    def jsonBody = builder {
//          update  updates
//    }

    def jsonBody = builder {
      method "game-updates"
      id "${EgsGamebotService.rpcCounter}"
      jsonrpc  "2.0"
      params {
        payload {
          update  updates
        }
      }

    }

    println "Built JSON string: ${builder.toPrettyString()}"
    println "Built JSON string: ${builder.toString()}"

    def http = new HTTPBuilder(lobbyServer.baseURL)
    http.auth.basic lobbyServer.lobbyUsername, lobbyServer.lobbyPassword

    def postBody = builder.toString()

    http.post(path: "${lobbyServer.gameBot}" , body: postBody, requestContentType: groovyx.net.http.ContentType.JSON) { resp ->
      println resp.responseData
    }

//    withRest(uri: lobbyServer.baseURL) {
//
//      auth.basic lobbyServer.lobbyUsername, lobbyServer.lobbyPassword
//
//      def json = post( path: "${lobbyServer.gameBot}/game-updates", payload: builder.content)
//
//      println "Returned JSON: ${json.responseData}"
//      responseData = json.responseData
//    }

    responseData
  }
}
