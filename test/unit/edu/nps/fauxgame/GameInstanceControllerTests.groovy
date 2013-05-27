package edu.nps.fauxgame



import org.junit.*
import grails.test.mixin.*

@TestFor(GameInstanceController)
@Mock(GameInstance)
@Ignore("Not implemented")
class GameInstanceControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/gameInstance/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.gameInstanceInstanceList.size() == 0
        assert model.gameInstanceInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.gameInstanceInstance != null
    }

    void testSave() {
        controller.save()

        assert model.gameInstanceInstance != null
        assert view == '/gameInstance/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/gameInstance/show/1'
        assert controller.flash.message != null
        assert GameInstance.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/gameInstance/list'

        populateValidParams(params)
        def gameInstance = new GameInstance(params)

        assert gameInstance.save() != null

        params.id = gameInstance.id

        def model = controller.show()

        assert model.gameInstanceInstance == gameInstance
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/gameInstance/list'

        populateValidParams(params)
        def gameInstance = new GameInstance(params)

        assert gameInstance.save() != null

        params.id = gameInstance.id

        def model = controller.edit()

        assert model.gameInstanceInstance == gameInstance
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/gameInstance/list'

        response.reset()

        populateValidParams(params)
        def gameInstance = new GameInstance(params)

        assert gameInstance.save() != null

        // test invalid parameters in update
        params.id = gameInstance.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/gameInstance/edit"
        assert model.gameInstanceInstance != null

        gameInstance.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/gameInstance/show/$gameInstance.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        gameInstance.clearErrors()

        populateValidParams(params)
        params.id = gameInstance.id
        params.version = -1
        controller.update()

        assert view == "/gameInstance/edit"
        assert model.gameInstanceInstance != null
        assert model.gameInstanceInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/gameInstance/list'

        response.reset()

        populateValidParams(params)
        def gameInstance = new GameInstance(params)

        assert gameInstance.save() != null
        assert GameInstance.count() == 1

        params.id = gameInstance.id

        controller.delete()

        assert GameInstance.count() == 0
        assert GameInstance.get(gameInstance.id) == null
        assert response.redirectedUrl == '/gameInstance/list'
    }
}
