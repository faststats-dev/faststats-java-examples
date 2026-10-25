plugins {
    id("net.neoforged.moddev") version "2.0.148"
}

neoForge {
    version = "26.3.0.77"
}

configurations.configureEach {
    resolutionStrategy.force("com.google.code.gson:gson:2.15.0")
}

dependencies {
    implementation("dev.faststats.metrics:neoforge:0.31.0+mc26.3")
    jarJar("dev.faststats.metrics:neoforge:0.31.0+mc26.3")
}
