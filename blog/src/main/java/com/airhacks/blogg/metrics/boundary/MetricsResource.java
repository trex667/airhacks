
package com.airhacks.blogg.metrics.boundary;

import java.util.concurrent.atomic.AtomicLong;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton
@Path("metrics")
public class MetricsResource {

    private AtomicLong CREATED_ARTICLES = new AtomicLong();
    private AtomicLong CREATION_ATTEMPTS = new AtomicLong();

    @GET
    public JsonObject stats() {
        return Json.createObjectBuilder().
                add("created", CREATED_ARTICLES.longValue()).
                add("attempts", CREATION_ATTEMPTS.longValue()).
                build();
    }

    public void onCreation() {
        CREATED_ARTICLES.incrementAndGet();
    }

    public void onAttempt() {
        CREATION_ATTEMPTS.incrementAndGet();
    }


}
