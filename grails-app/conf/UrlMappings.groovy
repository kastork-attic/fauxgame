class UrlMappings {

  static mappings = {

    // This is default GRAILS
    // We're using this, but it doesn't have
    // anything to do with the EGS API.

    "/$controller/$action?/$id?" {
      constraints {
        // apply constraints here
      }
    }

    // This is the games api
    // If we don't recognize gameTitle, then make a new
    // game title under that name.
    //
    //  action should be new or play

//    "/$gameTitle/$action" {
//
//    }

    "/"(view: "/index")
    "500"(view: '/error')
  }
}
