
package com.airhacks.ping.boundary;

import javax.ejb.Stateless;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class PingService {

    public String all() {
        throw new TodayNoPingException("the stack");
        // return " message " + System.currentTimeMillis();
    }

}
