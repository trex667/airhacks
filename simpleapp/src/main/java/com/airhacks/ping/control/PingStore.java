
package com.airhacks.ping.control;

import com.airhacks.ping.entity.Ping;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PingStore {

    @PersistenceContext
    EntityManager em;

    public void upsert(Ping ping) {
        this.em.merge(ping);
    }

    public List<Ping> all() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(PingStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.em.createNamedQuery("all", Ping.class).getResultList();
    }

}
