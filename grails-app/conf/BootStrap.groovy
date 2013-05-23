import edu.nps.fauxgame.Role
import edu.nps.fauxgame.User
import edu.nps.fauxgame.UserRole

class BootStrap {

  def init = { servletContext ->

    def u = new User(usernname: 'kastork@nps.edu',
                     password: 'foo',
                     enabled: true,
                     accountExpired: false,
                     accountLockec: false,
                     passwordExpired: false).save(flush: true)

    def r = new Role(authority: 'ROLE_ADMIN').save(flush: true)

    def ur = new UserRole(user: u, role: r).save(flush: true)
  }

  def destroy = {
  }
}
