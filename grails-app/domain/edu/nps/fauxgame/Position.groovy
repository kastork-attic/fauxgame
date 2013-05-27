package edu.nps.fauxgame

class Position {

  static belongsTo = [gameInstance: GameInstance]
  String roleParam
  String playerIdent
  String state = 'PEND'

  static constraints = {
    state inList: ['PEND', 'ATTN', 'OVER']
  }
}
