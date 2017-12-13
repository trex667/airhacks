
package com.airhacks.ping;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author airhacks.com
 */
public class ThreadDebug {

    @AroundInvoke
    public Object changeName(InvocationContext ic) throws Exception {
        String name = Thread.currentThread().getName();
        String methodName = ic.getMethod().toString();
        try {
            Thread.currentThread().setName(methodName + " -> " + name);
            return ic.proceed();
        } finally {
            Thread.currentThread().setName(name);
        }
    }


}
