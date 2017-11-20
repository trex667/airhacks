/*
 */
package com.airhacks.blog.posts.boundary;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class BlogsResource {
    
    private Client client;
    private WebTarget tut;
    
    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        this.tut = this.client.target("http://localhost:8080/blog/resources/blogs");
    }

    @Test
    public void fetchBlogs() {
        Response response = this.tut.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonArray blogs = response.readEntity(JsonArray.class);
        System.out.println("blogs = " + blogs);
    }

    @Test
    public void createBlog() {
        JsonObject blog = Json.createObjectBuilder().
                add("name", "testname").
                add("uri", "http://test.name").
                build();
        Response response = this.tut.request().post(Entity.json(blog));
        assertThat(response.getStatus(), is(201));
        String locationHeader = response.getHeaderString("Location");
        assertNotNull(locationHeader);
        System.out.println("locationHeader = " + locationHeader);

        Response fetchResponse = this.client.target(locationHeader).request().get();
        assertThat(fetchResponse.getStatus(), is(200));
        JsonObject blogAsJson = fetchResponse.readEntity(JsonObject.class);
        assertNotNull(blogAsJson);
        System.out.println("blogAsJson = " + blogAsJson);

    }


}
