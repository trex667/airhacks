
package com.airhacks.blogg.post.entity;

import java.util.UUID;
import javax.json.Json;
import javax.json.JsonObject;

public class Article {

    private String id;
    private String title;
    private String content;

    public Article(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
    }

    public Article(JsonObject input) {
        this(input.getString("title"), input.getString("content"));
    }

    public String getId() {
        return id;
    }



    public JsonObject toJSON() {
        return Json.createObjectBuilder().
                add("title", this.title).
                add("content", this.content).
                build();
    }


}
