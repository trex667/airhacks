
package com.airhacks.ping.boundary;

import com.airhacks.ping.control.PingStore;
import com.airhacks.ping.entity.Ping;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class PingService {

    @Inject
    PingStore store;

    public List<Ping> all() {
        return this.store.all();
    }

    public void save(Ping ping) {
        this.store.upsert(ping);
    }


}
