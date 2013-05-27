package edu.nps.fauxgame

import org.springframework.dao.DataIntegrityViolationException

class LobbyServerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [lobbyServerInstanceList: LobbyServer.list(params), lobbyServerInstanceTotal: LobbyServer.count()]
    }

    def create() {
        [lobbyServerInstance: new LobbyServer(params)]
    }

    def save() {
        def lobbyServerInstance = new LobbyServer(params)
        if (!lobbyServerInstance.save(flush: true)) {
            render(view: "create", model: [lobbyServerInstance: lobbyServerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'lobbyServer.label', default: 'LobbyServer'), lobbyServerInstance.id])
        redirect(action: "show", id: lobbyServerInstance.id)
    }

    def show(Long id) {
        def lobbyServerInstance = LobbyServer.get(id)
        if (!lobbyServerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lobbyServer.label', default: 'LobbyServer'), id])
            redirect(action: "list")
            return
        }

        [lobbyServerInstance: lobbyServerInstance]
    }

    def edit(Long id) {
        def lobbyServerInstance = LobbyServer.get(id)
        if (!lobbyServerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lobbyServer.label', default: 'LobbyServer'), id])
            redirect(action: "list")
            return
        }

        [lobbyServerInstance: lobbyServerInstance]
    }

    def update(Long id, Long version) {
        def lobbyServerInstance = LobbyServer.get(id)
        if (!lobbyServerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lobbyServer.label', default: 'LobbyServer'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (lobbyServerInstance.version > version) {
                lobbyServerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'lobbyServer.label', default: 'LobbyServer')] as Object[],
                          "Another user has updated this LobbyServer while you were editing")
                render(view: "edit", model: [lobbyServerInstance: lobbyServerInstance])
                return
            }
        }

        lobbyServerInstance.properties = params

        if (!lobbyServerInstance.save(flush: true)) {
            render(view: "edit", model: [lobbyServerInstance: lobbyServerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'lobbyServer.label', default: 'LobbyServer'), lobbyServerInstance.id])
        redirect(action: "show", id: lobbyServerInstance.id)
    }

    def delete(Long id) {
        def lobbyServerInstance = LobbyServer.get(id)
        if (!lobbyServerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lobbyServer.label', default: 'LobbyServer'), id])
            redirect(action: "list")
            return
        }

        try {
            lobbyServerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'lobbyServer.label', default: 'LobbyServer'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'lobbyServer.label', default: 'LobbyServer'), id])
            redirect(action: "show", id: id)
        }
    }
}
