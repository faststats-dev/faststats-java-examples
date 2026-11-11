package com.example;

import dev.faststats.bukkit.BukkitContext;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin extends JavaPlugin {
    // No token is passed here, it is read from the META-INF/faststats/ExamplePlugin.token resource
    // that the build writes from the FASTSTATS_TOKEN environment variable or the faststats.token Gradle property.
    // If the token is missing or empty, a warning is logged and FastStats stays disabled, the plugin keeps working.
    private final BukkitContext context = new BukkitContext.Factory(this)
            .metrics(factory -> factory.create()) // minimal metrics without custom metrics
            .create();

    @Override
    public void onEnable() {
        context.ready(); // start metrics submission
    }

    @Override
    public void onDisable() {
        context.shutdown(); // safely shut down configured services
    }
}
