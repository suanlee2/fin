package com.example.lib;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * create a class called MyClass.
 */
public final class MyClass {
    public MyClass() {

    }
    public static String holiday(final String name) {
        if (name != null) {
            JsonParser parser = new JsonParser();
            JsonObject r = parser.parse(name).getAsJsonObject();
            JsonArray array = r.getAsJsonArray("holidays");
            if (array.size() != 0) {
                JsonObject n = array.get(0).getAsJsonObject();
                return n.get("name").getAsString();
            }
        }
        return "Oops! Not a Holiday!";
    }

}
