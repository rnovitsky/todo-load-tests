package todo.config.dto

data class NfrConfig(
    val maxResponseTime: Int,
    val perc95ResponseTime: Int,
    val failedRequests: Double
)
