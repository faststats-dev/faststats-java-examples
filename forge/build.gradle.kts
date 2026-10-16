import net.minecraftforge.gradle.ForgeGradleExtension
import net.minecraftforge.gradle.MinecraftExtensionForProject
import net.minecraftforge.jarjar.gradle.JarJarExtension

plugins {
    id("net.minecraftforge.gradle") version "7.0.40"
    id("net.minecraftforge.jarjar") version "0.2.3"
}

val forgeGradle = the<ForgeGradleExtension>()
val minecraftExtension = the<MinecraftExtensionForProject>()
val jarJar = the<JarJarExtension>().register()

repositories {
    mavenLocal()
    minecraftExtension.mavenizer(this)
    maven(forgeGradle.forgeMaven)
    maven(forgeGradle.minecraftLibsMaven)
}

dependencies {
    compileOnly(minecraftExtension.dependency("net.minecraftforge:forge:26.3-66.0.12"))
    implementation("dev.faststats.metrics:forge:0.31.0-pre4+mc26.3")
    jarJar.configurationName("dev.faststats.metrics:forge:0.31.0-pre4+mc26.3")
}
