package edu.nps.fauxgame

class GameInstance {

  static belongsTo = [gameTitle: GameTitle, lobbyServer: LobbyServer]
  static hasMany = [positions: Position]
  Date dateCreated
  Date lastUpdated

  static constraints = {

  }

  String toString() {
    return "${gameTitle.uriToken}, $lobbyServer : ${positions}"
  }
}
