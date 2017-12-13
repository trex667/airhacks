
package com.airhacks.ping.boundary;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author airhacks.com
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PingService {

    public String all() {
        return " message " + System.currentTimeMillis();
    }

}
