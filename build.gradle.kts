plugins {
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.allopen") version "1.9.24"
    id("io.gatling.gradle") version "3.11.5.2"
}

group = "com.bhft"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    gatlingImplementation("com.sksamuel.hoplite:hoplite-core:2.7.5")
    gatlingImplementation("com.sksamuel.hoplite:hoplite-yaml:2.7.5")
}

kotlin {
    jvmToolchain(17)
}
