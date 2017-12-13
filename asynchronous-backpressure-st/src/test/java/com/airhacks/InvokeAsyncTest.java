package com.airhacks;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class InvokeAsyncTest {

    private Client client;
    private WebTarget tut;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        this.tut = this.client.target("http://localhost:8080/asynchronous-backpressure/resources/async");
    }

    /**
     * Server flooding
     */
    @Test
    public void asyncWithFuture() {
        List<Future<String>> queue = Stream.generate(() -> this.tut.request().async().get(String.class)).
                limit(1050).
                collect(Collectors.toList());

        queue.stream().
                map(this::unwrap).
                forEach(System.out::println);

    }

    @Test
    public void asyncWithInvoke() throws InterruptedException, ExecutionException {
        List<Future<String>> queue = Stream.generate(() -> this.tut.request().
                buildGet().
                submit(String.class)).
                limit(1000).
                collect(Collectors.toList());

        queue.stream().
                map(this::unwrap).
                forEach(System.out::println);
    }

    <T> T unwrap(Future<T> response) {
        try {
            return response.get();
        } catch (InterruptedException | ExecutionException ex) {
            throw new IllegalStateException(ex);
        }
    }


}
