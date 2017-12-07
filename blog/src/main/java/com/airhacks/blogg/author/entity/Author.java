
package com.airhacks.blogg.author.entity;

import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author airhacks.com
 */
public class Author {

    private String name;
    private String id;

    public Author(String name) {
        this.name = name;
        this.id = "42";
    }

    public String getId() {
        return id;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder().
                add("name", this.name).
                build();
    }


}
