plugins {
    id("com.gradleup.shadow")
}

java.toolchain.languageVersion = JavaLanguageVersion.of(25)

tasks.compileJava {
    options.release.set(25)
}

dependencies {
    implementation("net.minestom:minestom:2026.10.07-26.2")
    implementation("dev.faststats.metrics:minestom:0.30.2")
}

tasks.shadowJar {
    // optionally relocate faststats
    relocate("dev.faststats", "com.example.utils.faststats")
}
