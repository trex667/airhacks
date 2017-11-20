
package com.airhacks.blog.posts.boundary;

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
@Path("blogs")
public class BlogsResource {

    @Inject
    BlogsService service;

    @GET
    public JsonArray blogs() {
        JsonArrayBuilder retVal = Json.createArrayBuilder();
        service.allBlogs().
                stream().
                map(b -> b.toJsonWithContent("")).
                forEach(retVal::add);
        return retVal.build();
    }

}
