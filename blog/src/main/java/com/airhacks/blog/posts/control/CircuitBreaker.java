
package com.airhacks.blog.posts.control;

import java.util.concurrent.atomic.AtomicInteger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author airhacks.com
 */
public class CircuitBreaker {

    private AtomicInteger COUNTER = new AtomicInteger(0);

    @AroundInvoke
    public Object check(InvocationContext ic) throws Exception {
        long start = System.currentTimeMillis();
        try {
            if (COUNTER.intValue() > 3) {
                return null;
            }
            return ic.proceed();
        } catch (Exception ex) {
            COUNTER.incrementAndGet();
            throw ex;
        } finally {
            long duration = System.currentTimeMillis() - start;
            if (duration > 200) {
                COUNTER.incrementAndGet();
            }
        }
    }


}
