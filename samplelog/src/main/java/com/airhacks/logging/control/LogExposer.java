
package com.airhacks.logging.control;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

/**
 *
 * @author airhacks.com
 */
public class LogExposer {

    @Inject
    LogWriter log;

    @Produces
    public AirLog expose(InjectionPoint ip) {
        Class<?> declaringClass = ip.getMember().getDeclaringClass();

        return (long duration, String message) -> {
            log.write(declaringClass, duration, message);
        };

    }


}
