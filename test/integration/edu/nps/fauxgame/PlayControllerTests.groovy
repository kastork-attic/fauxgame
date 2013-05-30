package edu.nps.fauxgame

import static org.junit.Assert.*
import org.junit.*

class PlayControllerTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

  @Test
  void testPlay() {

//    String testResult = '{"stat":"OK","glst":{"cnt":"1","game":{"gid":"1"}},"update":[{"gameInstanceId":"1","gameTitle":"test-faux","gameVersion":"1.0","gamingId":"PlayerTwo","state":"ATTN"},{"gameInstanceId":"1","gameTitle":"test-faux","gameVersion":"1.0","gamingId":"PlayerOne","state":"PEND"}]}'

    def controller = new PlayController()

    controller.request.addParameter("gid", "1")
    controller.request.addParameter("role", "p1")
    controller.request.addParameter("app", "BRSR")
    controller.request.addParameter("gamingId", "PlayerOne")


    controller.serveGameClient()

//    println controller.response.contentAsString
//    println testResult

    //assertEquals(testResult, controller.response.contentAsString)

  }
}
