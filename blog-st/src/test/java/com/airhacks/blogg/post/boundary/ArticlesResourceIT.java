/*
 */
package com.airhacks.blogg.post.boundary;

import java.util.concurrent.TimeUnit;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class ArticlesResourceIT {

    private Client client;
    private WebTarget tut;

    @Before
    public void init() {
        this.client = ClientBuilder.newBuilder().
                connectTimeout(200, TimeUnit.MILLISECONDS).
                readTimeout(500, TimeUnit.MILLISECONDS).
                build();
        this.tut = this.client.target("http://localhost:8080/blog/resources/articles");
    }

    @Test
    public void crud() {
        JsonObject articleExpected = Json.createObjectBuilder().add("title", "about testing").
                add("content", "write STs to save time").
                build();

        Response postResponse = this.tut.request().post(json(articleExpected));
        assertThat(postResponse.getStatus(), is(201));
        String location = postResponse.getHeaderString("Location");
        assertNotNull(location);
        JsonObject articleActual = this.client.target(location).request().get(JsonObject.class);
        assertThat(articleActual.getString("title"), is(articleExpected.getString("title")));

        Response getResponse = this.tut.request().get();
        assertThat(getResponse.getStatusInfo().getFamily(), is(Response.Status.Family.SUCCESSFUL));
        JsonArray articlesAsJson = getResponse.readEntity(JsonArray.class);
        System.out.println("articlesAsJson = " + articlesAsJson);
        assertFalse(articlesAsJson.isEmpty());

    }


}
