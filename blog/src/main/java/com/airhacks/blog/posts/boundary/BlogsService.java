
package com.airhacks.blog.posts.boundary;

import com.airhacks.blog.posts.control.ContentCheck;
import com.airhacks.blog.posts.entity.Blog;
import com.airhacks.blog.posts.entity.Post;
import com.airhacks.monitoring.boundary.Counter;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.JsonObject;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class BlogsService {

    @Inject
    ContentCheck check;

    @Inject
    @Counter
    Event<String> monitoring;

    public List<Blog> allBlogs() {
        monitoring.fire("blog.fetch.count");
        List<Post> posts = Arrays.asList(new Post("rest", "..."), new Post("ms", ".ms.."));
        return Arrays.asList(new Blog("ag", "http://", posts), new Blog("ad", "https", posts));
    }

    public void create(JsonObject blog) {

        monitoring.fire("blog.created.count");
        String valid = this.check.isValid(blog.getString("name", "duke"));
        System.out.println("valid = " + valid);
    }

}
