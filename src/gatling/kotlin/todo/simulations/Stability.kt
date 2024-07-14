package todo.simulations

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.Simulation
import todo.config.gatlingConfig
import todo.protocols.Protocols.httpProtocol
import todo.scenarios.Scenarios.createTodosScenario

class Stability : Simulation() {
    init {
        setUp(
            with(gatlingConfig.gatling.stability) {
                createTodosScenario.injectOpen(
                    rampUsersPerSec(0.0)
                        .to(intensity)
                        .during(rampDuration),
                    constantUsersPerSec(intensity)
                        .during(stageDuration)
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
