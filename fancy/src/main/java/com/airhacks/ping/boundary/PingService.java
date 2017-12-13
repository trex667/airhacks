
package com.airhacks.ping.boundary;

import com.airhacks.ping.control.MessageService;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class PingService {

    @Inject
    Instance<MessageService> service;

    public String message() {
        return service.get().greetings();
    }

}
