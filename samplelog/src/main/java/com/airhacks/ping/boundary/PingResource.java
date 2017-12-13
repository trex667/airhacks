package com.airhacks.ping.boundary;

import com.airhacks.logging.control.AirLog;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Path("ping")
public class PingResource {

    @Inject
    AirLog LOG;

    @GET
    public String ping() {
        LOG.log(2, "nice");
        return "Enjoy Java EE 8!";
    }

}
