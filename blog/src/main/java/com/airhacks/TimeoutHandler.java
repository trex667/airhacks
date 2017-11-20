
package com.airhacks;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author airhacks.com
 */
public interface TimeoutHandler {

    public static void handle(AsyncResponse response) {
        Response r = Response.status(503).header("info", "server is overloaded").build();
        response.resume(r);
    }

}
