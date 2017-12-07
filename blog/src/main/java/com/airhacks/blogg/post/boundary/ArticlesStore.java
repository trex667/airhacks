
package com.airhacks.blogg.post.boundary;

import com.airhacks.blogg.post.control.InMemoryStore;
import com.airhacks.blogg.post.entity.Article;
import java.util.Collection;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ArticlesStore {

    @Inject
    InMemoryStore store;

    public Collection<Article> articles() {
        return store.all();
    }

    public Optional<Article> get(String id) {
        return this.store.get(id);
    }


    public void save(Article article) {
        this.store.store(article);
    }
}
