plugins {
    id("com.gradleup.shadow") version "9.6.1" apply false
    kotlin("jvm") version "2.4.21" apply false
}

subprojects {
    repositories {
        mavenCentral()
        maven("https://repo.faststats.dev/releases/")
    }

    apply {
        plugin("java")
        plugin("org.jetbrains.kotlin.jvm")
    }
}
