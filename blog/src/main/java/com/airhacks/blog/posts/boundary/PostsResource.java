
package com.airhacks.blog.posts.boundary;

import com.airhacks.blog.posts.entity.Post;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("posts")
public class PostsResource {

    @Inject
    PostsService service;

    @GET
    public JsonArray posts() {
        JsonArrayBuilder retVal = Json.createArrayBuilder();
        service.allPosts().
                stream().map(Post::toJson).
                forEach(retVal::add);
        return retVal.build();
    }

}
