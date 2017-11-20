
package com.airhacks.blog.posts.boundary;

import java.net.URI;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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

    @POST
    public Response createBlog(JsonObject blog, @Context UriInfo info) {
        URI uri = info.getAbsolutePathBuilder().
                path("/" + System.currentTimeMillis()).
                build();
        return Response.created(uri).build();

    }


}
