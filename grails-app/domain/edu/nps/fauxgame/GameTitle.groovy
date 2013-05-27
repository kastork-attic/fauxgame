package edu.nps.fauxgame

class GameTitle {

  static hasMany = [gameInstances: GameInstance]
  String uriToken
  String displayName
  Integer numberPositions = 0
  String gameVersion
  Date dateCreated
  Date lastUpdated

  static constraints = {
    uriToken blank: false, unique: true
    displayName blank: true
  }

  String toString() {
    return "$uriToken, $numberPositions"
  }

}
