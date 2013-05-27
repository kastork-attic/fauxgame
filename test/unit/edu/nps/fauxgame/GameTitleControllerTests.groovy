package edu.nps.fauxgame



import org.junit.*
import grails.test.mixin.*

@TestFor(GameTitleController)
@Mock(GameTitle)
@Ignore("Not implemented")
class GameTitleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/gameTitle/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.gameTitleInstanceList.size() == 0
        assert model.gameTitleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.gameTitleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.gameTitleInstance != null
        assert view == '/gameTitle/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/gameTitle/show/1'
        assert controller.flash.message != null
        assert GameTitle.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/gameTitle/list'

        populateValidParams(params)
        def gameTitle = new GameTitle(params)

        assert gameTitle.save() != null

        params.id = gameTitle.id

        def model = controller.show()

        assert model.gameTitleInstance == gameTitle
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/gameTitle/list'

        populateValidParams(params)
        def gameTitle = new GameTitle(params)

        assert gameTitle.save() != null

        params.id = gameTitle.id

        def model = controller.edit()

        assert model.gameTitleInstance == gameTitle
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/gameTitle/list'

        response.reset()

        populateValidParams(params)
        def gameTitle = new GameTitle(params)

        assert gameTitle.save() != null

        // test invalid parameters in update
        params.id = gameTitle.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/gameTitle/edit"
        assert model.gameTitleInstance != null

        gameTitle.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/gameTitle/show/$gameTitle.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        gameTitle.clearErrors()

        populateValidParams(params)
        params.id = gameTitle.id
        params.version = -1
        controller.update()

        assert view == "/gameTitle/edit"
        assert model.gameTitleInstance != null
        assert model.gameTitleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/gameTitle/list'

        response.reset()

        populateValidParams(params)
        def gameTitle = new GameTitle(params)

        assert gameTitle.save() != null
        assert GameTitle.count() == 1

        params.id = gameTitle.id

        controller.delete()

        assert GameTitle.count() == 0
        assert GameTitle.get(gameTitle.id) == null
        assert response.redirectedUrl == '/gameTitle/list'
    }
}
