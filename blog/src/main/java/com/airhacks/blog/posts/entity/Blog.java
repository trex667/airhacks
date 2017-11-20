
package com.airhacks.blog.posts.entity;

import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 *
 * @author airhacks.com
 */
public class Blog {

    private String name;
    private String uri;
    private List<Post> posts;

    public Blog(String name, String uri, List<Post> posts) {
        this.name = name;
        this.uri = uri;
        this.posts = posts;
    }

    public JsonObject toJsonWithContent(String content) {

        JsonArrayBuilder postsAsJson = Json.createArrayBuilder();
        this.posts.
                stream().
                filter(f -> f.containsString("rest")).
                map(Post::toJson).
                forEach(postsAsJson::add);

        return Json.createObjectBuilder().
                add("uri", this.uri).
                add("name", this.name).
                add("posts", postsAsJson).
                build();
    }


}
