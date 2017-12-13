/*
 */
package com.airhacks.elastic.logging;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class WriteLogTest {

    private Client client;

    private final String URI = "http://localhost:9200/airhacks/";
    private WebTarget tut;

    @Before
    public void initClient() {
        this.client = ClientBuilder.newClient();
        this.tut = this.client.target(URI);
    }

    @Test
    public void writeLogs() {
        for (int i = 0; i < 50; i++) {
            this.writeLog();
        }
    }

    void createIndex() {
        Response deleteResponse = this.tut.request().delete();
        assertThat(deleteResponse.getStatus(), is(200));
        Response putResponse = this.tut.request().put(json(Json.createObjectBuilder().build()));
        JsonObject putResult = putResponse.readEntity(JsonObject.class);
        System.out.println("createIndex response = " + putResult);
        Assume.assumeThat(putResponse.getStatus(), is(201));
    }


    @Test
    public void setupIndex() {
        this.createIndex();
        JsonObject configuration = configuration();
        System.out.println("configuration = " + configuration);
        Response putResponse = this.tut.path("_mappings").
                path("logs").
                request().
                put(json(configuration));
        JsonObject putResult = putResponse.readEntity(JsonObject.class);
        System.out.println("putResult = " + putResult);
        assertThat(putResponse.getStatus(), is(200));
    }

    JsonObject configuration() {
        JsonReader reader = Json.createReader(this.getClass().getResourceAsStream("/types.json"));
        return reader.readObject();
    }


    @Test
    public void writeLog() {
        JsonObject log = log(LocalDateTime.now(), "AirhacksBoundary", (long) (Math.random() * 100),
                "I'm alive" + System.nanoTime());
        Response postResponse = this.tut.
                path("logs").
                request().
                post(json(log));
        assertThat(postResponse.getStatus(), is(201));

    }


    JsonObject log(LocalDateTime date, String clazz, long duration, String message) {
        return Json.createObjectBuilder().
                add("time", date.toEpochSecond(ZoneOffset.UTC)).
                add("class", "message").
                add("duration", duration).
                add("message", message).
                build();
    }

}
