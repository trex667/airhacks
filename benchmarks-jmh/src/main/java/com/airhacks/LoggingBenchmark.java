package com.airhacks;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@Fork(1)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class LoggingBenchmark {

    @State(Scope.Benchmark)
    public static class LoggerState {

        Logger log;

        @Setup(Level.Trial)
        public void init() {
            this.log = Logger.getLogger(this.getClass().getName());
        }

    }

    @Benchmark
    public void log(LoggerState state) {
        state.log.info("test output");
    }

}
