
package com.airhacks.blogg.post.entity;

import javax.ejb.ApplicationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author airhacks.com
 */
@ApplicationException(rollback = true)
public class InvalidArticleException extends WebApplicationException {

    public InvalidArticleException(String message) {
        super(Response.status(400).header("reason", message).build());
    }


}
