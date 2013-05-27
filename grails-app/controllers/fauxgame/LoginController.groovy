package fauxgame

class LoginController {

  def index() {
    render "Blart"
  }

  def authfail() {
    println params
    println request.remoteUser
    render "arg!"
  }
}
