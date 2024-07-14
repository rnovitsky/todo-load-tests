package todo.simulations

import io.gatling.javaapi.core.CoreDsl.global
import io.gatling.javaapi.core.CoreDsl.incrementUsersPerSec
import io.gatling.javaapi.core.Simulation
import todo.config.gatlingConfig
import todo.protocols.Protocols.httpProtocol
import todo.scenarios.Scenarios.createTodosScenario

class MaxPerformance : Simulation() {
    init {
        setUp(
            with(gatlingConfig.gatling) {
                createTodosScenario.injectOpen(
                    incrementUsersPerSec(intensity / stages)
                        .times(stages)
                        .eachLevelLasting(stageDuration)
                        .separatedByRampsLasting(rampDuration)
                        .startingFrom(0.0)
                )
            }
        )
            .protocols(httpProtocol)
            .also {
                with(gatlingConfig.nfr) {
                    it.assertions(
                        global().responseTime().max().lte(maxResponseTime),
                        global().responseTime().percentile(95.0).lte(perc95ResponseTime),
                        global().failedRequests().percent().lte(failedRequests)
                    )
                }
            }
    }
}
