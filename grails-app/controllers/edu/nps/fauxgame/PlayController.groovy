package edu.nps.fauxgame

import edu.nps.faux.EgsGamebotService
import grails.plugin.springsecurity.userdetails.GrailsUser
import net.sf.json.JSONArray
import net.sf.json.JSONObject

class PlayController {

  def springSecurityService
  EgsProfileService egsProfileService
  EgsGamebotService egsGamebotService

  def index() {}

  def playEndpoint(String gameTitle, long gid, String role, String app, long ver) {

    GrailsUser grailsUser = springSecurityService.principal


    GameInstance gameInstance = GameInstance.get(params.gid)

    println gameInstance

    if (null != gameInstance) {

      Position positionInstance = Position.findByRoleParamAndGameInstance(params.role, gameInstance)

      redirect(
          action: 'client',
          params: [
              pid: positionInstance.id,
              gid: gameInstance.id
          ]
      )
    }

  }

  def serveGameClient(int pid, int gid) {
    println "serveGameClient: ${params}"

    GrailsUser grailsUser = springSecurityService.principal
    def authorities = grailsUser.authorities
    def positionInstance = Position.get(pid)
    println positionInstance
    def gameInstance = GameInstance.get(gid)
    println gameInstance

//    def returnedProfile = egsProfileService.profileGetViaAMQP(
    def returnedProfile = egsProfileService.profileGet(
        grailsUser.username,
        gameInstance.gameTitle.uriToken,
        gameInstance.gameTitle.gameVersion.toString(),
        positionInstance.roleParam,
        gameInstance.id.toString())

    println "Returned profile is: $returnedProfile"

    render(view: 'show', model:
        [
            positionInstance: positionInstance,
            gameInstance    : gameInstance,
            egsProfile      : returnedProfile,
            authorities     : authorities
        ])
  }

  def reload(int pid, int gid, String state) {

    Position positionInstance = Position.get(pid)
    GameInstance gameInstance = GameInstance.get(gid)

    positionInstance.setState(state)
    positionInstance.save(flush: true)

    def updates = [
        [
            gameInstanceId: gameInstance.id.toString(),
            gameTitle     : gameInstance.gameTitle.uriToken,
            gameVersion   : gameInstance.gameTitle.gameVersion.toString(),
            gamingId      : positionInstance.playerIdent,
            state         : state,
        ]
    ]

//    egsGamebotService.gameUpdateViaAMQP(1, updates)
    egsGamebotService.gameUpdates(1, updates)

    redirect(
        action: 'client',
        params: [
            pid  : pid,
            gid  : positionInstance.gameInstance.id
        ])
  }

  def toInit(int pid, int gid) {
    println "Init clicked: ${pid}, ${gid}"
    reload(pid, gid, "INIT")
  }

  def toPend(int pid, int gid) {
    println "Pend clicked: ${pid}, ${gid}"
    reload(pid, gid, "PEND")
  }

  def toAttn(int pid, int gid) {
    println "Attn clicked: ${pid}, ${gid}"
    reload(pid, gid, "ATTN")
  }

  def toOver(int pid, int gid) {
    println "Over clicked: ${pid}, ${gid}"
    reload(pid, gid, "OVER")
  }
}
