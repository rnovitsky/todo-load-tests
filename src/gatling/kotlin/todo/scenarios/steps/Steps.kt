package todo.scenarios.steps

import io.gatling.javaapi.core.CoreDsl.StringBody
import io.gatling.javaapi.core.CoreDsl.responseTimeInMillis
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import todo.config.gatlingConfig
import todo.scenarios.steps.Steps.Payloads.todoItem

object Steps {
    val createTodo =
        http("Create TODO")
            .post(TODOS_ENDPOINT)
            .body(StringBody(todoItem))

            .check(
                status().shouldBe(201),
                responseTimeInMillis().lte(gatlingConfig.nfr.maxResponseTime)
            )

    internal object Payloads {
        val todoItem =
            """
                {
                    "id": #{id},
                    "text": "load test",
                    "completed": true
                }
            """.trimIndent()
    }
}

private const val TODOS_ENDPOINT = "/todos"
