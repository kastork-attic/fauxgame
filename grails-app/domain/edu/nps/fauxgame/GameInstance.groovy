package edu.nps.fauxgame

class GameInstance {

  static belongsTo = [gameTitle: GameTitle, lobbyServer: LobbyServer]
  static hasMany = [position: Position]
  Date dateCreated
  Date lastUpdated

  static constraints = {

  }
}
