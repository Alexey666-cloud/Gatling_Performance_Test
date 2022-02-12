package perf

import io.gatling.core.Predef._
import io.gatling.core.feeder.BatchableFeederBuilder

object feeders {
  val depart: BatchableFeederBuilder[String] = csv("depart.csv").random.eager
  val arrive: BatchableFeederBuilder[String] = csv("arrive.csv").random.eager
}
