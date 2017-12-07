
package com.airhacks.blogg.post.entity;

import javax.json.Json;
import javax.json.JsonObject;

public class Article {

    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article(JsonObject input) {
        this.title = input.getString("title");
        this.content = input.getString("content");
    }


    public JsonObject toJSON() {
        return Json.createObjectBuilder().
                add("title", this.title).
                add("content", this.content).
                build();
    }


}
