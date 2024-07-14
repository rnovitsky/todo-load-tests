package todo.simulations

import io.gatling.javaapi.core.CoreDsl.incrementUsersPerSec
import io.gatling.javaapi.core.Simulation
import todo.config.gatlingConfig
import todo.protocols.Protocols.httpProtocol
import todo.scenarios.Scenarios.createTodosScenario

class MaxPerformance : Simulation() {
    init {
        setUp(
            with(gatlingConfig.gatling.maxPerformance) {
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
    }
}
