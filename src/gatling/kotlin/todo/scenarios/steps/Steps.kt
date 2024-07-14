package todo.scenarios.steps

import io.gatling.javaapi.core.CoreDsl.StringBody
import io.gatling.javaapi.core.CoreDsl.responseTimeInMillis
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import todo.config.gatlingConfig
import todo.scenarios.steps.dto.TodoItem

object Steps {
    val createTodo =
        http("Create TODO")
            .post(TODOS_ENDPOINT)
            .body(
                StringBody {
                    TodoItem(
                        id = it.getLong("id"),
                        text = "load test",
                        completed = true
                    ).run { Json.encodeToString(this) }
            })

            .check(
                status().shouldBe(201),
                responseTimeInMillis().lte(gatlingConfig.nfr.maxResponseTime)
            )
}

private const val TODOS_ENDPOINT = "/todos"
