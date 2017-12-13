
package com.airhacks.ping.boundary;

import javax.ejb.ApplicationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author airhacks.com
 */
@ApplicationException(rollback = false)
public class TodayNoPingException extends WebApplicationException {

    public TodayNoPingException(String message) {
        super(Response.status(200).header("reason", message).build());
    }


}
