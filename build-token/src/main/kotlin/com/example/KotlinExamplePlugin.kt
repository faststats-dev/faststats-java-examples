package com.example

import dev.faststats.bukkit.BukkitContext
import org.bukkit.plugin.java.JavaPlugin

class KotlinExamplePlugin : JavaPlugin() {
    // No token is passed here, it is read from the META-INF/faststats/ExamplePlugin.token resource
    // that the build writes from the FASTSTATS_TOKEN environment variable or the faststats.token Gradle property.
    // If the token is missing or empty, a warning is logged and FastStats stays disabled, the plugin keeps working.
    private val context = BukkitContext.Factory(this)
        .metrics { factory -> factory.create() } // minimal metrics without custom metrics
        .create()

    override fun onEnable() {
        context.ready() // start metrics submission
    }

    override fun onDisable() {
        context.shutdown() // safely shut down configured services
    }
}
