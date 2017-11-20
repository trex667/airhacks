
package com.airhacks.logging.boundary;

import com.sun.istack.internal.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author airhacks.com
 */
public class LoggerExposer {

    @Produces
    public ALogger expose(InjectionPoint ip) {

        Class<?> clazz = ip.getMember().getDeclaringClass();
        return (component, content) -> Logger.getLogger(clazz).info(component + content);

    }


}
