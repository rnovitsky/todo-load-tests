package todo.feeders

object Feeders {
    private val allowedIds = (0..Long.MAX_VALUE).iterator()

    val todoIdFeeder =
        generateSequence {
            mapOf("id" to allowedIds.nextLong())
        }.iterator()
}
