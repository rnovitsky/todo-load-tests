package todo.simulations

import io.gatling.javaapi.core.*
import io.gatling.javaapi.core.CoreDsl.*
import todo.protocols.Protocols.httpProtocol
import todo.scenarios.Scenarios.createTodosScenario

class TodoSimulation : Simulation() {
    init {
        setUp(
            createTodosScenario.injectOpen(rampUsers(10).during(10))
        ).protocols(httpProtocol)
    }
}
