package com.airhacks.ping.boundary;

import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("ping")
public class PingResource {

    @Inject
    Instance<PingService> lazy;

    @GET
    public String ping() {
        return lazy.get().message();
    }

}
