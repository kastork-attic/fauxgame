package edu.nps.fauxgame

class PlayController {

  def springSecurityService

  def index() {}

  def playEndpoint() {
    // This is the player's entry point.
    // must be covered by CAS authentication
    // once we have an email from CAS, then get
    // player data with EGS gamebot.
//    println "playEndpoint"
//    println params
//    println springSecurityService.principal
    GameInstance gameInstance = GameInstance.get(params.gid)

    println gameInstance

    if (null != gameInstance) {
      Position positionInstance = Position.findByRoleParamAndGameInstance(params.role, gameInstance)


      redirect(action: 'client', params: [pid: positionInstance.id,  gid: gameInstance.id])
    }
  }

  def serveGameClient(int pid, int gid) {
    // All params delivered (i.e., the CAS verification and EGS poll for
    // user info is complete already, and the values are passed in here as
    // params.
    println "serveGameContent"
    println params

    def positionInstance = Position.get(pid)
    println positionInstance
    def gameInstance = GameInstance.get(gid)
    println gameInstance
    render (view: 'show', model:[positionInstance: positionInstance, gameInstance: gameInstance])
  }

}
