package edu.nps.fauxgame

import java.util.HashMap.Entry
import edu.nps.fauxgame.GameTitle
import grails.converters.*

class NewController {

  def newGameService

  def index() {}

  def createGame() {

    log.debug "Game creation requested."
    log.debug params

    def responseMap = newGameService.createGame(params)

    if(responseMap.stat.equals("ERROR"))
    {
      render(status:400, text: responseMap.message)
      return
    }

    if(params.fmt == 'json') {
      render responseMap as JSON
    } else {
      render(status: 400, text: 'Unknown format requested ${params.fmt}')
    }

  }

}
