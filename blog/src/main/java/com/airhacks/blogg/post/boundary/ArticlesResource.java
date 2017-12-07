
package com.airhacks.blogg.post.boundary;

import com.airhacks.blogg.post.entity.Article;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

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


}
