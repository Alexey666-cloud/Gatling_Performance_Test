  package perf

  import io.gatling.core.Predef._
  import io.gatling.core.structure.{ScenarioBuilder}
  import perf.feeders._

  object Scenario{
    def apply() = new Scenario().scn
  }

  class Scenario {

    val groupMainPage = group("group_main_page"){
      exec(actions.getMainPage)
        .exec(actions.getMainPage2)
        .exec(actions.getMainPage3)
    }
    val scn: ScenarioBuilder = scenario("first_scn")
      .feed(depart)
      .feed(arrive)

      .exec(groupMainPage)
      .exec(actions.loginWebTours)
      .exec(actions.goToFlights)
      .exec(actions.addFlight)
      .exec(actions.addTime)
      .exec(actions.payTicket)
      .exec(actions.getMainPage)

  }

