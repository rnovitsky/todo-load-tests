package todo.config.dto

data class GatlingConfig(
    val baseUrl: String,
    val maxPerformance: PerfTestConfig,
    val stability: PerfTestConfig
)
