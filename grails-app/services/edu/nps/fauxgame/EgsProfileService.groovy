package edu.nps.fauxgame

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseDecorator
import net.sf.json.JSONObject


class EgsProfileService {

  // https://globalecco.org/api/secure/jsonws/egs-portlet.gamingprofile/get?email=member@domain&title=someTitle&ver=xx&role&gid=xxxx
  def profileGet(String userEmail, String gameTitle, String gameVersion, String gameRole, String gameId) {


    def lobbyServer = LobbyServer.get(1)

    JSONObject responseData

    def http = new HTTPBuilder(lobbyServer.baseURL)
    http.auth.basic lobbyServer.lobbyUsername, lobbyServer.lobbyPassword

    println "Will GET: ${lobbyServer.profile}"
    def foo = http.get( path: "${lobbyServer.profile}",
        query: [ email: userEmail,
                 title: gameTitle,
                 ver: gameVersion,
                 role: gameRole,
                 gid: gameId]
    ) { HttpResponseDecorator json ->
      println "Returned response: ${json.allHeaders}"
      responseData = json.responseData
    }

    println "Foo is $foo"







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
