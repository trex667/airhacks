
package com.airhacks.ping.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "all", query = "SELECT p FROM Ping p")
public class Ping {

    @Id
    @GeneratedValue
    private long id;

    private String message;
    private long duration;

    public Ping(String message, long duration) {
        this.message = message;
        this.duration = duration;
    }

    public Ping(JsonObject input) {
        this.message = input.getString("message");
        this.duration = input.getInt("duration");
    }

    public Ping() {
    }

    public JsonObject toJson() {

        return Json.createObjectBuilder().
                add("message", this.message).
                add("duration", this.duration).
                build();

    }


}
