
package com.airhacks.monitoring.boundary;

import java.util.concurrent.atomic.AtomicLong;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("monitoring")
public class MonitoringResource {

    @Inject
    MonitoringStore store;

    @GET
    public JsonObject monitoring() {
        JsonObjectBuilder retVal = Json.createObjectBuilder();
        this.store.getMetrics().entrySet().forEach(e -> retVal.add(e.getKey(), e.getValue().longValue()));
        return retVal.build();
    }

    @GET
    @Path("{name}")
    public String metric(@PathParam("name") String name) {
        return String.valueOf(this.store.getMetrics().getOrDefault(name, new AtomicLong(-1)).intValue());
    }



}
