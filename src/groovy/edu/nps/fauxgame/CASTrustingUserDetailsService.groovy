package edu.nps.fauxgame

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import edu.nps.fauxgame.UserAdminService

/**
 * Trust that a user who passes CAS authentication is
 * okay.  Create the user and assign the "ROLE_PLAYER" authority.
 * Client controllers will have to be aware and ask for reauthentication
 * in some circumstances.
 */
class CASTrustingUserDetailsService implements GrailsUserDetailsService {

  static final List NO_ROLES = [new GrantedAuthorityImpl(SpringSecurityUtils.NO_ROLE)]

  UserAdminService userAdminService

  UserDetails loadUserByUsername(String username, boolean loadRoles)
  throws UsernameNotFoundException {
    return loadUserByUsername(username)
  }

  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User.withTransaction { status ->

      User user = User.findByUsername(username)
      if (!user) {
        user = userAdminService.createUserWithGamePlayerRole(username)
      }
      if (!user) throw new UsernameNotFoundException("This really should never happen.")

      def authorities = user.authorities.collect {
        new GrantedAuthorityImpl(it.authority)
      }

      return new GrailsUser(user.username, user.password, user.enabled,
          !user.accountExpired, !user.passwordExpired,
          !user.accountLocked, authorities ?: NO_ROLES, user.id)
    }
  }

}
