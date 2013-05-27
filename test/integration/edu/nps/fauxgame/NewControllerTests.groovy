package edu.nps.fauxgame

import static org.junit.Assert.*
import org.junit.*

class NewControllerTests {

  @Before
  void setUp() {
    // Setup logic here
  }

  @After
  void tearDown() {
    // Tear down logic here
  }

  @Test
  void testNoExistingGameTitleJSON() {

    String testResult = '{"stat":"OK","glst":{"cnt":"1","game":{"gid":"1"}}}'

    def controller = new NewController()

    controller.request.addParameter("gameTitle", "test-faux")
    controller.request.addParameter("p1", "PlayerOne")
    controller.request.addParameter("p2", "PlayerTwo")
    controller.request.addParameter("app", "BRSR")
    controller.request.addParameter("dbg", "0")
    controller.request.addParameter("lang", "en")
    controller.request.addParameter("fmt", "json")

    controller.createGame()

//    println controller.response.contentAsString
//    println testResult

    assertEquals(testResult, controller.response.contentAsString)

  }

}
