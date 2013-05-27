package edu.nps.fauxgame

import org.springframework.dao.DataIntegrityViolationException

class GameInstanceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [gameInstanceInstanceList: GameInstance.list(params), gameInstanceInstanceTotal: GameInstance.count()]
    }

    def create() {
        [gameInstanceInstance: new GameInstance(params)]
    }

    def save() {
        def gameInstanceInstance = new GameInstance(params)
        if (!gameInstanceInstance.save(flush: true)) {
            render(view: "create", model: [gameInstanceInstance: gameInstanceInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'gameInstance.label', default: 'GameInstance'), gameInstanceInstance.id])
        redirect(action: "show", id: gameInstanceInstance.id)
    }

    def show(Long id) {
        def gameInstanceInstance = GameInstance.get(id)
        if (!gameInstanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameInstance.label', default: 'GameInstance'), id])
            redirect(action: "list")
            return
        }

        [gameInstanceInstance: gameInstanceInstance]
    }

    def edit(Long id) {
        def gameInstanceInstance = GameInstance.get(id)
        if (!gameInstanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameInstance.label', default: 'GameInstance'), id])
            redirect(action: "list")
            return
        }

        [gameInstanceInstance: gameInstanceInstance]
    }

    def update(Long id, Long version) {
        def gameInstanceInstance = GameInstance.get(id)
        if (!gameInstanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameInstance.label', default: 'GameInstance'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (gameInstanceInstance.version > version) {
                gameInstanceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'gameInstance.label', default: 'GameInstance')] as Object[],
                          "Another user has updated this GameInstance while you were editing")
                render(view: "edit", model: [gameInstanceInstance: gameInstanceInstance])
                return
            }
        }

        gameInstanceInstance.properties = params

        if (!gameInstanceInstance.save(flush: true)) {
            render(view: "edit", model: [gameInstanceInstance: gameInstanceInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'gameInstance.label', default: 'GameInstance'), gameInstanceInstance.id])
        redirect(action: "show", id: gameInstanceInstance.id)
    }

    def delete(Long id) {
        def gameInstanceInstance = GameInstance.get(id)
        if (!gameInstanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameInstance.label', default: 'GameInstance'), id])
            redirect(action: "list")
            return
        }

        try {
            gameInstanceInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'gameInstance.label', default: 'GameInstance'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'gameInstance.label', default: 'GameInstance'), id])
            redirect(action: "show", id: id)
        }
    }
}
