package todo.protocols

import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpProtocolBuilder
import todo.config.gatlingConfig

object Protocols {
    val httpProtocol: HttpProtocolBuilder =
        http.baseUrl(gatlingConfig.gatling.baseUrl)
            .contentTypeHeader("application/json")
            .acceptHeader("*/*")
            .disableFollowRedirect()
}
