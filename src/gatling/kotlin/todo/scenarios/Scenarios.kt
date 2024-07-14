package todo.scenarios

import io.gatling.javaapi.core.CoreDsl.scenario
import todo.feeders.Feeders.todoIdFeeder
import todo.scenarios.steps.Steps.createTodo

object Scenarios {
    val createTodosScenario =
        scenario("Create TODOs")
            .feed(todoIdFeeder)
            .exec(createTodo)
}
