import edu.nps.fauxgame.Role
import edu.nps.fauxgame.User
import edu.nps.fauxgame.UserRole
import edu.nps.fauxgame.LobbyServer

class BootStrap
{

  def init = { servletContext ->

    environments {

      development {

        def lobby = LobbyServer.get(1)

        if (null == lobby)
        {
          new LobbyServer(baseURL: 'http://localhost:9000',
              profile: '/serverapi/profile/get',
              gameBot: '/serverapi/game-updates',
              lobbyUsername: 'fauxgame@games.globalecco.org',
              lobbyPassword: 'fauxgame').save(flush: true)
        }
      }

      localProduction {

        def lobby = LobbyServer.get(1)

        if (null == lobby)
        {
          new LobbyServer(baseURL: 'http://kas-local-liferay.ngrok.com',
              profile: '/api/secure/jsonws/egs-portlet.gamingprofile/get',
              gameBot: '/api/secure/jsonws/egs-portlet.gamebot',
              lobbyUsername: 'games@globalecco.org',
              lobbyPassword: 'wibble').save(flush: true)
        }
      }

      beanstalk {
        def lobby = LobbyServer.get(1)
        if (null == lobby)
        {
          new LobbyServer(baseURL: 'http://ecco-lobby.elasticbeanstalk.com',
              profile: '/serverapi/profile/get',
              gameBot: '/serverapi/game-updates',
              lobbyUsername: 'fauxgame@games.globalecco.org',
              lobbyPassword: 'fauxgame').save(flush: true)

          println "===  CREATED LOBBY AT CONFIG FOR:  http://ecco-lobby.elasticbeanstalk.com"
        }

      }
    }

    // password login is not used or available
    // in this app since we're using CAS
    def u = new User(username: 'kastork@nps.edu',
        password: 'foo',
        enabled: true,
        accountExpired: false,
        accountLocked: false,
        passwordExpired: false).save(flush: true)

    def pr = new Role(authority: 'ROLE_PLAYER').save(flush: true)
    def ur = new UserRole(user: u, role: pr).save(flush: true)

    def ar = new Role(authority: 'ROLE_ADMIN').save(flush: true)
    ur = new UserRole(user: u, role: ar).save(flush: true)

    u = new User(username: 'admin_user',
        password: 'foo',
        enabled: true,
        accountExpired: false,
        accountLocked: false,
        passwordExpired: false).save(flush: true)

    ur = new UserRole(user: u, role: pr).save(flush: true)

    ur = new UserRole(user: u, role: ar).save(flush: true)
  }

  def destroy = {
  }
}
