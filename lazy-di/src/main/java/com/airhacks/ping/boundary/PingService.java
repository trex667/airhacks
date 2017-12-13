
package com.airhacks.ping.boundary;

import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PingService {

    static AtomicInteger COUNTER = new AtomicInteger(0);

    @PostConstruct
    public void create() {
        COUNTER.incrementAndGet();
    }

    public String message() {
        return "hey ping " + COUNTER.get();
    }

    @PreDestroy
    public void method() {
        COUNTER.decrementAndGet();
    }

}
