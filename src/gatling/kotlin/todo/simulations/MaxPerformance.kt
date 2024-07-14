package todo.simulations

import io.gatling.javaapi.core.CoreDsl.incrementUsersPerSec
import io.gatling.javaapi.core.Simulation
import todo.protocols.Protocols.httpProtocol
import todo.scenarios.Scenarios.createTodosScenario

class MaxPerformance : Simulation() {
    init {
        setUp(
            createTodosScenario.injectOpen(
                incrementUsersPerSec(100.0)
                    .times(5)
                    .eachLevelLasting(10)
                    .separatedByRampsLasting(10)
                    .startingFrom(0.0)
            )
        ).protocols(httpProtocol)
    }
}
