package com.example

import dev.faststats.ErrorTracker
import dev.faststats.data.Metric
import dev.faststats.forge.FastStatsForge
import net.minecraftforge.fml.common.Mod
import java.util.concurrent.atomic.AtomicInteger

@Mod("example_mod")
class KotlinExampleMod {
    private val gameCount = AtomicInteger()

    private val context = FastStatsForge.builder("example-mod") // your mod id as defined in fabric.mod.json
        .token("YOUR_TOKEN_HERE")
        .requireConsent() // Require explicit user consent, this will open the consent screen on first start
        .metrics { factory ->
            factory
                .addMetric(Metric.number("game_count") { gameCount.get() })
                .addMetric(Metric.string("server_version") { "1.0.0" })
                .onFlush { gameCount.set(0) }
                .create()
        }
        .errorTrackerService(ERROR_TRACKER)
        .build()

    fun startGame() {
        gameCount.incrementAndGet()
    }

    companion object {
        val ERROR_TRACKER: ErrorTracker = ErrorTracker.contextAware()
    }
}
