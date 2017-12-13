package com.airhacks.backpressurce;

import java.util.concurrent.TimeUnit;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class Blocker {

    @Asynchronous
    public void slow() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
