pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.allopen") version kotlinVersion
        id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
    }
}

rootProject.name = "todo-load-tests"
