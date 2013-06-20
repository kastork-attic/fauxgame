package edu.nps.fauxgame

import edu.nps.faux.EgsGamebotService
import net.sf.json.JSONArray
import net.sf.json.JSONObject
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser

class PlayController {

  def springSecurityService
  EgsProfileService egsProfileService
  EgsGamebotService egsGamebotService

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
    def authorities = grailsUser.authorities
    def positionInstance = Position.get(pid)
    println positionInstance
    def gameInstance = GameInstance.get(gid)
    println gameInstance

    def returnedProfile = egsProfileService.profileGet(grailsUser.username,
        gameInstance.gameTitle.uriToken,
        gameInstance.gameTitle.gameVersion.toString(),
        positionInstance.roleParam,
        gameInstance.id.toString())


    render(view: 'show', model:
        [positionInstance: positionInstance,
            gameInstance: gameInstance,
            egsProfile: returnedProfile,
            authorities: authorities
        ])
  }

  def reload(int pid, String state) {

    Position positionInstance = Position.get(pid)
    GameInstance gameInstance = GameInstance.get(positionInstance.gameInstanceId)

    positionInstance.setState(state)
    positionInstance.save(flush: true)

    def updates = [[gameInstanceId: gameInstance.id.toString(),
        gameTitle: gameInstance.gameTitle.uriToken,
        gameVersion: gameInstance.gameTitle.gameVersion.toString(),
        gamingId: positionInstance.playerIdent,
        state: state,
    ]]

    egsGamebotService.gameUpdates(1, updates)

    redirect(action: 'client',
        params: [pid: pid, gid: positionInstance.gameInstance.id])
  }

  def toInit(int pid) {
    println "Init clicked: ${pid}"
    reload(pid, "INIT")
  }

  def toPend(int pid) {
    println "Pend clicked: ${pid}"
    reload(pid, "PEND")
  }

  def toAttn(int pid) {
    println "Attn clicked: ${pid}"
    reload(pid, "ATTN")
  }

  def toOver(int pid) {
    println "Over clicked: ${pid}"
    reload(pid, "OVER")
  }
}
