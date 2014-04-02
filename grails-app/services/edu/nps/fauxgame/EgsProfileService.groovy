package edu.nps.fauxgame

import groovyx.net.http.HTTPBuilder
import net.sf.json.JSONObject


class EgsProfileService {

  // https://globalecco.org/api/secure/jsonws/egs-portlet.gamingprofile/get?email=member@domain&title=someTitle&ver=xx&role&gid=xxxx
  def profileGet(String userEmail, String gameTitle, String gameVersion, String gameRole, String gameId) {


    def lobbyServer = LobbyServer.get(1)

    JSONObject responseData

    def http = new HTTPBuilder(lobbyServer.baseURL)
    http.auth.basic lobbyServer.lobbyUsername, lobbyServer.lobbyPassword
    http.get( path: "${lobbyServer.profile}",
        query: [ email: userEmail,
                 title: gameTitle,
                 ver: gameVersion,
                 role: gameRole,
                 gid: gameId]
    ) { json ->
      println "Returned response: ${json.responseData}"
      responseData = json.responseData
    }







//    withRest(uri: lobbyServer.baseURL) {
//
//      auth.basic lobbyServer.lobbyUsername, lobbyServer.lobbyPassword
//
//      println "GET: ${lobbyServer.profile}"
//
//
//      def json = get( path: "${lobbyServer.profile}",
//                      query: [ email: userEmail,
//                          title: gameTitle,
//                          ver: gameVersion,
//                          role: gameRole,
//                          gid: gameId]
//      )
//
//      println "Returned JSON: ${json.responseData}"
//      responseData = json.responseData
//    }

    responseData
  }
}
