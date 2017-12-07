
package com.airhacks.blogg.post.boundary;

import com.airhacks.blogg.post.control.InMemoryStore;
import com.airhacks.blogg.post.entity.Article;
import com.airhacks.blogg.post.entity.InvalidArticleException;
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
        if (!article.isValid()) {
            throw new InvalidArticleException("Article is not valid");
        }
        this.store.store(article);
    }
}
