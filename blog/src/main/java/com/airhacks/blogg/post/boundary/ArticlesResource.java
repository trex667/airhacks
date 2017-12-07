
package com.airhacks.blogg.post.boundary;

import com.airhacks.blogg.post.entity.Article;
import java.net.URI;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Stateless
@Path("articles")
public class ArticlesResource {

    @Inject
    ArticlesStore store;

    @GET
    public Response articles() {
        JsonArrayBuilder retVal = Json.createArrayBuilder();
        long start = System.currentTimeMillis();
        store.articles().
                stream().
                map(Article::toJSON).
                forEach(retVal::add);

        return Response.ok(retVal.build()).
                header("executed-in", System.currentTimeMillis() - start).
                build();

    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") String id) {
        Optional<Article> optionalArticle = this.store.get(id);
        Optional<JsonObject> result = optionalArticle.map(Article::toJSON);
        if (result.isPresent()) {
            return Response.ok(result.get()).build();
        } else {
            return Response.noContent().build();
        }

    }


    @POST
    public Response save(JsonObject input, @Context UriInfo info) {
        Article article = new Article(input);

        URI uri = info.getAbsolutePathBuilder().
                path("/" + article.getId()).
                build();
        this.store.save(article);
        return Response.created(uri).build();
    }



}
