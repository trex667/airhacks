
package com.airhacks.configurator.control;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author airhacks.com
 */
public class Configurator {

    @Produces
    public Integer number(InjectionPoint ip) {
        String name = ip.getMember().getName();
        String number = getValue(name, "-1");
        return Integer.parseInt(number);
    }

    String getValue(String key, String defaultValue) {
        return System.getenv().
                getOrDefault(key, System.getProperty(key, defaultValue));
    }


}
