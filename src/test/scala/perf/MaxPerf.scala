package perf

import io.gatling.core.Predef._

class MaxPerf extends Simulation {
  setUp(
    Scenario().inject(
      incrementUsersPerSec(2) // интенсивность на ступень intensity / stagesNumber
        .times(5) // Количество ступеней stagesNumber
        .eachLevelLasting(20) // Длительность полки stageDuration
        .separatedByRampsLasting(380) // Длительность разгона rampDuration
        .startingFrom(1))
  )
    .protocols(httpProtocol)
    .maxDuration(3000)
}

