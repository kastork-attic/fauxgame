package edu.nps.fauxgame



import org.junit.*
import grails.test.mixin.*

@TestFor(LobbyServerController)
@Mock(LobbyServer)
class LobbyServerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/lobbyServer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.lobbyServerInstanceList.size() == 0
        assert model.lobbyServerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.lobbyServerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.lobbyServerInstance != null
        assert view == '/lobbyServer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/lobbyServer/show/1'
        assert controller.flash.message != null
        assert LobbyServer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/lobbyServer/list'

        populateValidParams(params)
        def lobbyServer = new LobbyServer(params)

        assert lobbyServer.save() != null

        params.id = lobbyServer.id

        def model = controller.show()

        assert model.lobbyServerInstance == lobbyServer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/lobbyServer/list'

        populateValidParams(params)
        def lobbyServer = new LobbyServer(params)

        assert lobbyServer.save() != null

        params.id = lobbyServer.id

        def model = controller.edit()

        assert model.lobbyServerInstance == lobbyServer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/lobbyServer/list'

        response.reset()

        populateValidParams(params)
        def lobbyServer = new LobbyServer(params)

        assert lobbyServer.save() != null

        // test invalid parameters in update
        params.id = lobbyServer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/lobbyServer/edit"
        assert model.lobbyServerInstance != null

        lobbyServer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/lobbyServer/show/$lobbyServer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        lobbyServer.clearErrors()

        populateValidParams(params)
        params.id = lobbyServer.id
        params.version = -1
        controller.update()

        assert view == "/lobbyServer/edit"
        assert model.lobbyServerInstance != null
        assert model.lobbyServerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/lobbyServer/list'

        response.reset()

        populateValidParams(params)
        def lobbyServer = new LobbyServer(params)

        assert lobbyServer.save() != null
        assert LobbyServer.count() == 1

        params.id = lobbyServer.id

        controller.delete()

        assert LobbyServer.count() == 0
        assert LobbyServer.get(lobbyServer.id) == null
        assert response.redirectedUrl == '/lobbyServer/list'
    }
}
