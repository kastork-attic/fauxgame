package edu.nps.fauxgame

import net.sf.json.JSONObject


class EgsProfileService {

  // https://globalecco.org/api/secure/jsonws/egs-portlet.gamingprofile/get?email=member@domain&title=someTitle&ver=xx&role&gid=xxxx
  def profileGet(String userEmail, String gameTitle, String gameVersion, String gameRole, String gameId) {


    def lobbyServer = LobbyServer.get(1)
    JSONObject responseData

    withRest(uri: lobbyServer.baseURL) {

      auth.basic lobbyServer.lobbyUsername, lobbyServer.lobbyPassword

      def json = get( path: "${lobbyServer.profile}/get",
                      query: [ email: userEmail,
                          title: gameTitle,
                          ver: gameVersion,
                          role: gameRole,
                          gid: gameId]
      )

      println "Returned JSON: ${json.responseData}"
      responseData = json.responseData
    }

    responseData
  }
}
