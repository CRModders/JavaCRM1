package io.github.crmodders.javacrm1;

import org.hjson.JsonArray;
import org.hjson.JsonObject;
import org.hjson.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class HJsonUtil {
    private HJsonUtil() { }

    public static boolean contains(JsonObject object, String key) {
        return object.names().contains(key);
    }

    public static JsonValue getOrThrow(JsonObject object, String key) throws RuntimeException {
        if (contains(object, key))
            return object.get(key);
        throw new RuntimeException("Key `%s` not found in JsonObject".formatted(key));
    }

    public static List<String> getStringArray(JsonObject object, String key) throws RuntimeException {
        JsonArray array = getOrThrow(object, key).asArray();
        List<String> list = new ArrayList<>();
        array.forEach(value -> list.add(value.asString()));
        return list;
    }
}
