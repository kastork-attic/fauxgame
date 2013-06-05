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

    "/$gameTitle/new" {
      controller = "new"
      action = "createGame"
    }

    "/$gameTitle/play" {
      controller = "play"
      action = "playEndpoint"
    }

    "/play/client" {
      controller = "play"
      action = "serveGameClient"
    }

    "/play/toInit/$pid" {
      controller = "play"
      action = "toInit"
    }

    "/play/toPend/$pid" {
      controller = "play"
      action = "toPend"
    }

    "/play/toAttn/$pid" {
      controller = "play"
      action = "toAttn"
    }

    "/play/toOver/$pid" {
      controller = "play"
      action = "toOver"
    }

    "/"(view: "/index")
    "500"(view: '/error')
  }
}
