import edu.nps.fauxgame.Role
import edu.nps.fauxgame.User
import edu.nps.fauxgame.UserRole
import edu.nps.fauxgame.LobbyServer

class BootStrap {

  def init = { servletContext ->

    def u = new User(username: 'kastork@nps.edu',
        password: 'foo',
        enabled: true,
        accountExpired: false,
        accountLocked: false,
        passwordExpired: false).save(flush: true)

    def r = new Role(authority: 'ROLE_PLAYER').save(flush: true)
    def ur = new UserRole(user: u, role: r).save(flush: true)

    r = new Role(authority: 'ROLE_ADMIN').save(flush: true)
    ur = new UserRole(user: u, role: r).save(flush: true)

    def lobby = new LobbyServer(baseURL: 'http://localhost:8080',
        profile: '/api/secure/jsonws/egs-portlet.gamingprofile',
        gameBot: '/api/secure/jsonws/egs-portlet.gamebot',
        lobbyUsername: 'games@globalecco.org',
        lobbyPassword: 'letmein').save(flush: true)
  }

  def destroy = {
  }
}
