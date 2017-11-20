
package com.airhacks.blog.posts.control;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author airhacks.com
 */
public class ContentCheck {

    private Client client;
    private WebTarget validationURI;

    @PostConstruct
    public void initClient() {
        this.client = ClientBuilder.newClient();
        this.validationURI = this.client.target("http://localhost:8080/content-check/resources/validation");
    }

    public String isValid(String content) {
        Response response = this.validationURI.request().post(Entity.text(content));
        return response.readEntity(String.class);
    }


}
