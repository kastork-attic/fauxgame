package edu.nps.fauxgame

class GameTitle {

  static hasMany = [gameInstances: GameInstance]
  String uriToken
  String displayName
  Integer numberPositions = 0
  String gameVersion
  Date dateCreated
  Date lastUpdated
  Boolean supportsSelfPlay

  static constraints = {
    uriToken blank: false, unique: true
    displayName blank: true
    supportsSelfPlay nullable: true
  }

  String toString() {
    return "$uriToken, $numberPositions"
  }

}
