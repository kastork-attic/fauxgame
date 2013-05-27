package edu.nps.fauxgame

class LobbyServer {

  String baseURL
  String profile
  String gameBot
  String lobbyUsername
  String lobbyPassword

  static constraints = {
  }

  String toString() {
    "$baseURL"
  }
}
