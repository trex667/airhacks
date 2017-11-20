
package com.airhacks.blog.posts.boundary;

import com.airhacks.blog.posts.entity.Blog;
import com.airhacks.blog.posts.entity.Post;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class BlogsService {

    public List<Blog> allBlogs() {
        List<Post> posts = Arrays.asList(new Post("rest", "..."), new Post("ms", ".ms.."));
        return Arrays.asList(new Blog("ag", "http://", posts), new Blog("ad", "https", posts));
    }

}
