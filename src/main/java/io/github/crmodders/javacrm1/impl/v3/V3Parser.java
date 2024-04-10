package io.github.crmodders.javacrm1.impl.v3;

import io.github.crmodders.javacrm1.*;
import io.github.crmodders.javacrm1.api.AbstractParser;

import org.hjson.JsonArray;
import org.hjson.JsonObject;
import org.hjson.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class V3Parser extends AbstractParser {
    public V3Parser() {
        super(3);
    }

    @Override
    public V3RepoSpec parse(String hjson) {
        JsonObject repo = JsonValue.readHjson(hjson).asObject();

        int specVersion = HJsonUtil.getOrThrow(repo, "specVersion").asInt();
        if (specVersion != this.crmVersion)
            throw new RuntimeException("Incompatible spec version. Got %s, expected %s.".formatted(specVersion, this.crmVersion));

        String rootId = HJsonUtil.getOrThrow(repo, "rootId").asString();
        int lastUpdated = HJsonUtil.getOrThrow(repo, "lastUpdated").asInt();

        List<String> repoDeps = HJsonUtil.getStringArray(repo, "deps");

        JsonArray mods = HJsonUtil.getOrThrow(repo, "mods").asArray();
        List<V3ModSpec> modSpecs = new ArrayList<>();
        mods.forEach(mod -> modSpecs.add(parseModSpec(mod.asObject(), rootId)));

        return new V3RepoSpec(specVersion, rootId, lastUpdated, repoDeps, modSpecs);
    }

    public V3ModSpec parseModSpec(JsonObject object, String defaultSource) {
        String id = HJsonUtil.getOrThrow(object, "id").asString();
        String name = HJsonUtil.getOrThrow(object, "name").asString();
        String desc = HJsonUtil.getOrThrow(object, "desc").asString();
        List<String> authors = HJsonUtil.getStringArray(object, "authors");
        String latest = HJsonUtil.getOrThrow(object, "latest").asString();
        List<V3ModVersionSpec> versions = parseModVersionSpecs(HJsonUtil.getOrThrow(object, "versions").asArray(), defaultSource);

        JsonObject ext = HJsonUtil.contains(object, "ext") ?
                object.get("ext").asObject() :
                new JsonObject();

        return new V3ModSpec(
                id,
                name,
                desc,
                authors,
                latest,
                versions,
                JsonObject.unmodifiableObject(ext)
        );
    }

    public List<V3ModVersionSpec> parseModVersionSpecs(JsonArray versions, String defaultSource) {
        return versions.values().stream().map(value -> {
            var object = value.asObject();
            String version = HJsonUtil.getOrThrow(object, "version").asString();
            String gameVersion = HJsonUtil.getOrThrow(object, "gameVersion").asString();
            String url = HJsonUtil.getOrThrow(object, "url").asString();

            JsonArray deps = HJsonUtil.getOrThrow(object, "deps").asArray();
            List<V3DependencySpec> dependencySpecs = new ArrayList<>();
            deps.forEach(dep -> dependencySpecs.add(parseDependencySpec(dep.asObject(), defaultSource)));

            JsonObject ext = HJsonUtil.contains(object, "ext") ?
                    object.get("ext").asObject() :
                    new JsonObject();

            return new V3ModVersionSpec(version, gameVersion, url, dependencySpecs, ext);
        }).toList();
    }

    public V3DependencySpec parseDependencySpec(JsonObject object, String defaultSource) {
        String id = HJsonUtil.getOrThrow(object, "id").asString();
        String version = HJsonUtil.getOrThrow(object, "version").asString();
        String source = object.getString("source", defaultSource);
        return new V3DependencySpec(id, version, source);
    }
}
