package edu.nps.fauxgame

import org.springframework.dao.DataIntegrityViolationException

class GameTitleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [gameTitleInstanceList: GameTitle.list(params), gameTitleInstanceTotal: GameTitle.count()]
    }

    def create() {
        [gameTitleInstance: new GameTitle(params)]
    }

    def save() {
        def gameTitleInstance = new GameTitle(params)
        if (!gameTitleInstance.save(flush: true)) {
            render(view: "create", model: [gameTitleInstance: gameTitleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'gameTitle.label', default: 'GameTitle'), gameTitleInstance.id])
        redirect(action: "show", id: gameTitleInstance.id)
    }

    def show(Long id) {
        def gameTitleInstance = GameTitle.get(id)
        if (!gameTitleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameTitle.label', default: 'GameTitle'), id])
            redirect(action: "list")
            return
        }

        [gameTitleInstance: gameTitleInstance]
    }

    def edit(Long id) {
        def gameTitleInstance = GameTitle.get(id)
        if (!gameTitleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameTitle.label', default: 'GameTitle'), id])
            redirect(action: "list")
            return
        }

        [gameTitleInstance: gameTitleInstance]
    }

    def update(Long id, Long version) {
        def gameTitleInstance = GameTitle.get(id)
        if (!gameTitleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameTitle.label', default: 'GameTitle'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (gameTitleInstance.version > version) {
                gameTitleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'gameTitle.label', default: 'GameTitle')] as Object[],
                          "Another user has updated this GameTitle while you were editing")
                render(view: "edit", model: [gameTitleInstance: gameTitleInstance])
                return
            }
        }

        gameTitleInstance.properties = params

        if (!gameTitleInstance.save(flush: true)) {
            render(view: "edit", model: [gameTitleInstance: gameTitleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'gameTitle.label', default: 'GameTitle'), gameTitleInstance.id])
        redirect(action: "show", id: gameTitleInstance.id)
    }

    def delete(Long id) {
        def gameTitleInstance = GameTitle.get(id)
        if (!gameTitleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameTitle.label', default: 'GameTitle'), id])
            redirect(action: "list")
            return
        }

        try {
            gameTitleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'gameTitle.label', default: 'GameTitle'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'gameTitle.label', default: 'GameTitle'), id])
            redirect(action: "show", id: id)
        }
    }
}
