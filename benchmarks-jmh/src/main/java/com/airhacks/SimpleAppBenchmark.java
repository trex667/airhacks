package com.airhacks;

import java.util.concurrent.TimeUnit;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
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
public class SimpleAppBenchmark {

    @State(Scope.Benchmark)
    public static class LoggerState {

        WebTarget tut;

        @Setup(Level.Trial)
        public void init() {
            this.tut = ClientBuilder.newClient().target("http://localhost:8080/simpleapp/resources/ping");
        }

    }

    @Benchmark
    public void fetchData(LoggerState state) {
        String result = state.tut.request().get(String.class);
        System.out.println("result = " + result);
    }

}
