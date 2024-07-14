package todo.config.dto

data class PerfTestConfig(
    val intensity: Double,
    val rampDuration: Long,
    val stageDuration: Long,
    val stages: Int = 1
)
