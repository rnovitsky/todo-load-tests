package todo.protocols

import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpProtocolBuilder

object Protocols {
    val httpProtocol: HttpProtocolBuilder =
        http.baseUrl("http://localhost:8080")
            .contentTypeHeader("application/json")
            .acceptHeader("*/*")
            .disableFollowRedirect()
}
