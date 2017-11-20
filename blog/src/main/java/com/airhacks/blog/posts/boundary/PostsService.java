
package com.airhacks.blog.posts.boundary;

import com.airhacks.blog.posts.entity.Post;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class PostsService {

    public List<Post> allPosts() {
        return Arrays.asList(new Post("rest", "..."), new Post("ms", ".ms.."));
    }

}
