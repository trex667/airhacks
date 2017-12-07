
package com.airhacks.blogg.post.control;

import com.airhacks.blogg.post.entity.Article;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author airhacks.com
 */
@ApplicationScoped
public class InMemoryStore {

    private ConcurrentHashMap<String, Article> store;

    @PostConstruct
    public void init() {
        this.store = new ConcurrentHashMap<>();
    }

    public void store(Article article) {
        this.store.put(article.getId(), article);
    }

    public Collection<Article> all() {
        return this.store.values();

    }

    public Optional<Article> get(String id) {
        return Optional.ofNullable(this.store.get(id));
    }
}
