// Place your Spring DSL code here
beans = {
  userDetailsService(edu.nps.fauxgame.CASTrustingUserDetailsService) {
    userAdminService = ref('userAdminService')
  }
}
