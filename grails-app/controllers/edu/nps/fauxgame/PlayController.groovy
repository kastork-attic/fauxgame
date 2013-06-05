package edu.nps.fauxgame

import net.sf.json.JSONObject
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser

class PlayController {

  def springSecurityService
  EgsProfileService egsProfileService

  def index() {}

  def playEndpoint() {

    GrailsUser grailsUser = springSecurityService.principal
    GameInstance gameInstance = GameInstance.get(params.gid)

    println gameInstance

    if (null != gameInstance) {

      Position positionInstance = Position.findByRoleParamAndGameInstance(params.role, gameInstance)


      redirect(action: 'client',
               params: [pid: positionInstance.id,
                        gid: gameInstance.id])
    }

  }

  def serveGameClient(int pid, int gid) {

    GrailsUser grailsUser = springSecurityService.principal

    def positionInstance = Position.get(pid)
    println positionInstance
    def gameInstance = GameInstance.get(gid)
    println gameInstance

    def returnedProfile = egsProfileService.profileGet(grailsUser.username,
        gameInstance.gameTitle.uriToken,
        gameInstance.gameTitle.gameVersion.toString(),
        positionInstance.roleParam,
        gameInstance.id.toString())


    render(view: 'show', model: [positionInstance: positionInstance, gameInstance: gameInstance, egsProfile: returnedProfile])
  }

}
