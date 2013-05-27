package edu.nps.fauxgame

class GameTitle {

  static hasMany = [gameInstances: GameInstance]
  String uriToken
  String displayName
  Date dateCreated
  Date lastUpdated

  static constraints = {
    uriToken blank: false, unique: true
    displayName blank: true
  }
}
