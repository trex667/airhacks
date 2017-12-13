package com.airhacks.backpressurce;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Path("async")
@Stateless
public class AsyncResource {

    @Inject
    Blocker blocker;

    @GET
    public String get() {
        this.blocker.slow();
        return "+";
    }

}
