package com.airhacks.ping.boundary;

import com.airhacks.ping.entity.Ping;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("ping")
public class PingResource {

    @Inject
    PingService ping;

    @GET
    public JsonArray ping() {
        JsonArrayBuilder retVal = Json.createArrayBuilder();
        List<Ping> all = this.ping.all();
        all.stream().map(Ping::toJson).forEach(retVal::add);
        return retVal.build();

    }

    @POST
    public void save(JsonObject input) {
        this.ping.save(new Ping(input));
    }


}
