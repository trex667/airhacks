
package com.airhacks.blog.posts.boundary;

import com.airhacks.TimeoutHandler;
import com.airhacks.blog.posts.entity.Blog;
import com.airhacks.blog.posts.entity.Post;
import com.airhacks.logging.boundary.ALogger;
import com.airhacks.porcupine.execution.boundary.Dedicated;
import java.net.URI;
import java.util.Arrays;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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

    @Inject
    @Dedicated
    ExecutorService mes;

    @Inject
    ALogger LOG;

    @GET
    @Produces({"highly/optimized", "application/json"})
    public void blogs(@Suspended AsyncResponse response) {
        LOG.log("BlogsResource", "invoked");
        response.setTimeout(500, TimeUnit.MILLISECONDS);
        response.setTimeoutHandler(TimeoutHandler::handle);
        supplyAsync(this::getBlogs, mes).thenAccept(response::resume);

    }


    public JsonArray getBlogs() {

        JsonArrayBuilder retVal = Json.createArrayBuilder();
        service.allBlogs().
                stream().
                map(b -> b.toJson()).
                forEach(retVal::add);
        return retVal.build();
    }


    @GET
    @Path("{name}")
    public Response blog(@PathParam("name") String name) {
        return Response.ok(new Blog("generated", name, Arrays.asList(new Post("adsf", "adf"))).toJson()).
                header("posts-ids", "[2,3,4]").
                build();
    }


    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createBlog(JsonObject blog, @Context UriInfo info) {
        this.service.create(blog);

        URI uri = info.getAbsolutePathBuilder().
                path("/" + System.currentTimeMillis()).
                build();
        return Response.created(uri).entity("zusatz info").build();

    }


}
