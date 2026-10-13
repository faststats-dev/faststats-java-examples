plugins {
    id("net.fabricmc.fabric-loom") version "1.15-SNAPSHOT"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(25)

tasks.compileJava {
    options.release.set(25)
}

dependencies {
    compileOnly("net.fabricmc:fabric-loader:0.19.5")
    implementation("dev.faststats.metrics:fabric:0.31.0-pre2+mc26.3")
    include("dev.faststats.metrics:fabric:0.31.0-pre2+mc26.3")
    minecraft("com.mojang:minecraft:26.3")
}
