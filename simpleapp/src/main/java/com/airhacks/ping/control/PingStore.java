
package com.airhacks.ping.control;

import com.airhacks.ping.entity.Ping;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PingStore {

    @PersistenceContext
    EntityManager em;

    public void upsert(Ping ping) {
        this.em.merge(ping);
    }

    public List<Ping> all() {
        return this.em.createNamedQuery("all", Ping.class).getResultList();
    }

}
