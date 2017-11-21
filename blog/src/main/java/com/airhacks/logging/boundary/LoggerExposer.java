
package com.airhacks.logging.boundary;

import java.util.logging.Logger;
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
        return (component, content) -> Logger.getLogger(clazz.getName()).info(String.format("[%s] %s", component, content));

    }


}
