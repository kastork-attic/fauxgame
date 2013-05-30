package edu.nps.fauxgame

class UserAdminService {


  def createUserWithGamePlayerRole(String username) {

    User u = new User(username: username,
        password: 'no_password',
        enabled: true,
        accountExpired: false,
        accountLocked: false,
        passwordExpired: false).save(flush: true)

    Role r = Role.findByAuthority('ROLE_PLAYER')

    UserRole ur = new UserRole(user: u, role: r).save(flush:true)

    u
  }
}
