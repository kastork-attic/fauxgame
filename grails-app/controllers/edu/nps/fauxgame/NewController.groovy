package edu.nps.fauxgame

import java.util.HashMap.Entry
import edu.nps.fauxgame.GameTitle
import grails.converters.*

class NewController {


  def index() {}

  def createGame() {
    println "Game creation requested."
    println params

    def positions = [:]
    params.entrySet().each { e ->
//      println e.key
//      println e.value
      if (!["gameTitle", "app", "dbg", "lang", "fmt"].contains(e.key)) {
        positions[e.key] = e.value
      }
    }

    println positions

    def t = GameTitle.findByUriToken(params.gameTitle)

    if (null == t) {
      t = new GameTitle(uriToken: params.gameTitle, displayName: params.gameTitle, numberPositions: positions.size()).save(flush: true)
    } else {
      if( t.numberPositions != positions.size()) {
        response.status = 400
        render "Attempt to redefine a game title's position structure."
      }
    }

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

    println newGame

    if(params.fmt == 'json') {
      render buildNewGameResponse(newGame) as JSON
    } else {
      render(status: 400, text: 'Unknown format requested ${params.fmt}')
    }

  }

  def buildNewGameResponse(GameInstance game) {
    [stat: "OK", glst: [cnt: "1", game: [gid: "$game.id"]]]
  }
}
