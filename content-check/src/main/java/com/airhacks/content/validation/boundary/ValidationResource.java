
package com.airhacks.content.validation.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Path("validation")
public class ValidationResource {

    @POST
    public String validate(String input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ValidationResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.valueOf(input.contains("duke"));
    }


}
