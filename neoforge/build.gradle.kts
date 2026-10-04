plugins {
    id("net.neoforged.moddev") version "2.0.148"
}

neoForge {
    version = "26.1.2.76"
}

configurations.configureEach {
    resolutionStrategy.force("com.google.code.gson:gson:2.14.0")
}

dependencies {
    implementation("dev.faststats.metrics:neoforge:0.30.2")
    jarJar("dev.faststats.metrics:neoforge:0.30.2")
}
