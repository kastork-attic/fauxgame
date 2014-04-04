package edu.nps.fauxgame

class NewGameService {

  def createGame(Map parameterMap) {

    def positions = [:]
    parameterMap.entrySet().each { e ->
      if (!["gameTitle", "app", "dbg", "lang", "fmt", "ver", "action", "controller"].contains(e.key)) {
        positions[e.key] = e.value
      }
    }

    log.debug positions

    def t = GameTitle.findByUriTokenAndGameVersion(parameterMap.gameTitle, parameterMap.ver)

    if (null == t) {

      t = new GameTitle(
          uriToken: parameterMap.gameTitle,
          displayName: parameterMap.gameTitle,
          numberPositions: positions.size(),
          gameVersion: parameterMap.ver
      ).save(flush: true)

    } else {
      if( t.numberPositions != positions.size()) {
        return [stat: "ERROR", message: "Attempt to redefine a game title's position structure."]
      }
    }

    // TODO Be intelligent about lobby server configurations
    def newGame = new GameInstance(
        gameTitle: t,
        lobbyServer: LobbyServer.get(1)
    )
    newGame.save(flush: true)

    positions.each { k, v ->
      Position p = new Position(gameInstance: newGame, roleParam: k, playerIdent: v)
      newGame.addToPositions(p)
      p.save(flush: true)
    }

    newGame.save(flush: true)

    log.debug "Game instance saved: $newGame"

    return buildNewGameResponse(newGame)
  }

  private def buildNewGameResponse(GameInstance game) {


    def positions = game.positions

    def updateRecords = []
    positions.eachWithIndex { p, idx ->
      boolean attn = false
      if(idx == 0) {
        attn = true
      }
      updateRecords.add(buildUpdateForPosition(game, p, attn))
    }
    def result = [stat: "OK", glst: [cnt: "1", game: [gid: "$game.id"]], update: updateRecords]

  }

  private def buildUpdateForPosition(GameInstance g, Position p, boolean attn) {

    if(attn) {
      p.state = 'ATTN'
      p.save(flush: true)
    }

    [gameInstanceId: "$g.id", gameTitle: g.gameTitle.uriToken, gameVersion: "$g.gameTitle.gameVersion", gamingId: p.playerIdent, state: p.state]
  }

}
