
package com.airhacks.blogg.post.boundary;

import com.airhacks.blogg.post.entity.Article;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class ArticlesStore {

    public List<Article> articles() {
        return Arrays.asList(new Article("a1", "duke"), new Article("a2", "fluke"));
    }
}
