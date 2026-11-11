plugins {
    id("com.gradleup.shadow")
}

java.toolchain.languageVersion = JavaLanguageVersion.of(25)

tasks.compileJava {
    options.release.set(25)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.3.build.+")
    implementation("dev.faststats.metrics:bukkit:0.32.2")
}

// Resolve the FastStats token at build time instead of hardcoding it in the source code.
// This is not meant to hide the token (it is still readable in the jar), it keeps it out of the code
// so forks of this repository don't send data to your FastStats project.
// Lookup order:
//   1. FASTSTATS_TOKEN environment variable (e.g. a GitHub Actions repository secret)
//   2. faststats.token Gradle property (-Pfaststats.token=...)
//   3. faststats.token file in the project directory (git-ignored, one token per project for local builds)
// Never put the token in the project's own gradle.properties, as that file is committed.
val faststatsToken = providers.environmentVariable("FASTSTATS_TOKEN")
    .orElse(providers.gradleProperty("faststats.token"))
    .orElse(providers.fileContents(layout.projectDirectory.file("faststats.token")).asText.map { it.trim() })
    .orElse("")

tasks.processResources {
    val token = faststatsToken
    inputs.property("faststatsToken", token)

    // The SDK reads the token from META-INF/faststats/<id>.token when no token is passed to the factory.
    // <id> is the plugin name from plugin.yml (or the plugin/mod id on Velocity, Sponge, Fabric, Forge and NeoForge).
    doLast {
        val file = destinationDir.resolve("META-INF/faststats/ExamplePlugin.token")
        file.parentFile.mkdirs()
        file.writeText(token.get())
    }
}

tasks.shadowJar {
    // optionally relocate faststats
    relocate("dev.faststats", "com.example.utils.faststats")
}
