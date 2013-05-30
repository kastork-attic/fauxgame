package fauxgame

import org.springframework.security.cas.authentication.CasAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetailsService

class LoginController {

  CasAuthenticationProvider casAuthenticationProvider

  def index() {
    render "Blart"
  }

  def authfail() {
    println params
    println request.remoteUser
    render "arg!"

  }
}
