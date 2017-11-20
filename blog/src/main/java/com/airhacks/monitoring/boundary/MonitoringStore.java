
package com.airhacks.monitoring.boundary;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;

/**
 *
 * @author airhacks.com
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class MonitoringStore {

    private ConcurrentHashMap<String, AtomicLong> COUNTERS;

    @PostConstruct
    public void init() {
        this.COUNTERS = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, AtomicLong> getMetrics() {
        return this.COUNTERS;
    }


    public void onNewEvent(@Observes @Counter String monitoring) {
        AtomicLong counter = this.COUNTERS.putIfAbsent(monitoring, new AtomicLong(1));
        if (counter != null) {
            counter.incrementAndGet();
        }
    }


}
