package todo.config.dto

data class GatlingConfig(
    val baseUrl: String,
    val intensity: Double,
    val rampDuration: Long,
    val stageDuration: Long,
    val stages: Int
)
