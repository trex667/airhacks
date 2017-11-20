
package com.airhacks.blog.posts.entity;

import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author airhacks.com
 */
public class Post {

    private String title;
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder().
                add("title", this.title).
                add("content", this.content).
                build();
    }

    public boolean containsString(String content) {
        return this.title.contains(content) || this.content.contains(content);
    }


}
