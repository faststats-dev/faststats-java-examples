plugins {
    id("com.gradleup.shadow")
}

java.toolchain.languageVersion = JavaLanguageVersion.of(17)

tasks.compileJava {
    options.release.set(17)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("net.md-5:bungeecord-api:26.1-R0.1-SNAPSHOT")
    implementation("dev.faststats.metrics:bungeecord:0.31.0-pre4")
}

tasks.shadowJar {
    // optionally relocate faststats
    relocate("dev.faststats", "com.example.utils.faststats")
}
