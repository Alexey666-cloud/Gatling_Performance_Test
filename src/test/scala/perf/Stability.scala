package perf

import io.gatling.core.Predef._

class Stability extends Simulation {
  setUp(
    Scenario().inject(
      rampUsersPerSec(0) to 2 during 225, //разгон
      constantUsersPerSec(2) during 3375 //полка
    )
  ).protocols(httpProtocol)
    .maxDuration(3600) //длительность теста = разгон + полка
}

