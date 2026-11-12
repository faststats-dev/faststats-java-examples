package com.example;

import com.google.inject.Inject;
import dev.faststats.ErrorTracker;
import dev.faststats.FastStatsContext;
import dev.faststats.data.Metric;
import dev.faststats.sponge.FastStatsSponge;
import org.jspecify.annotations.Nullable;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.StartedEngineEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.builtin.jvm.Plugin;

import java.util.concurrent.atomic.AtomicInteger;

@Plugin("example")
public class ExamplePlugin {
    public static final ErrorTracker ERROR_TRACKER = ErrorTracker.contextAware();
    private @Inject FastStatsSponge contextBuilder;
    private @Nullable FastStatsContext context = null;

    private final AtomicInteger gameCount = new AtomicInteger();

    @Listener
    public void onServerStart(final StartedEngineEvent<Server> event) {
        this.context = contextBuilder.builder()
                .token("YOUR_TOKEN_HERE")
                .errorTrackerService(ERROR_TRACKER)
                // .metrics(Metrics.Factory::create) // Define a minimal metrics instance without any custom metrics
                .metrics(factory -> factory
                        // Custom metrics require a corresponding data source in your project settings
                        .addMetric(Metric.number("game_count", gameCount::get))
                        .addMetric(Metric.string("server_version", () -> "1.0.0"))

                        // #onFlush is invoked after successful metrics submission
                        // This is useful for cleaning up cached data
                        .onFlush(() -> gameCount.set(0)) // reset game count on flush

                        .create())
                .build();
        context.ready(); // start metrics and errors submission
    }

    @Listener
    public void onServerStop(final StoppingEngineEvent<Server> event) {
        if (context != null) context.shutdown(); // safely shut down configured services
    }

    public void startGame() {
        gameCount.incrementAndGet();
    }
}
