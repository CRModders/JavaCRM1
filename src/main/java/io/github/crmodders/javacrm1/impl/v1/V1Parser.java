package io.github.crmodders.javacrm1.impl.v1;

import io.github.crmodders.javacrm1.*;
import io.github.crmodders.javacrm1.api.AbstractParser;
import io.github.crmodders.javacrm1.api.DependencySpec;
import io.github.crmodders.javacrm1.api.ModSpec;
import io.github.crmodders.javacrm1.api.RepoSpec;
import org.hjson.JsonArray;
import org.hjson.JsonObject;
import org.hjson.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class V1Parser extends AbstractParser {
    public V1Parser() {
        super("1");
    }

    @Override
    public RepoSpec parse(String hjson) {
        JsonObject repo = JsonValue.readHjson(hjson).asObject();

        int specVersion = HJsonUtil.getOrThrow(repo, "specVersion").asInt();
        String rootId = HJsonUtil.getOrThrow(repo, "rootId").asString();
        int lastUpdated = HJsonUtil.getOrThrow(repo, "lastUpdated").asInt();

        JsonArray mods = HJsonUtil.getOrThrow(repo, "mods").asArray();
        List<ModSpec> modSpecs = new ArrayList<>();
        mods.forEach(mod -> modSpecs.add(parseModSpec(mod.asObject(), rootId)));

        return new RepoSpec(specVersion, rootId, lastUpdated, modSpecs);
    }

    public ModSpec parseModSpec(JsonObject object, String defaultSource) {
        String id = HJsonUtil.getOrThrow(object, "id").asString();
        String name = HJsonUtil.getOrThrow(object, "name").asString();
        String desc = HJsonUtil.getOrThrow(object, "desc").asString();
        List<String> authors = HJsonUtil.getStringArray(object, "authors");
        String version = HJsonUtil.getOrThrow(object, "version").asString();
        String gameVersion = HJsonUtil.getOrThrow(object, "gameVersion").asString();
        String url = HJsonUtil.getOrThrow(object, "url").asString();

        JsonArray deps = HJsonUtil.getOrThrow(object, "deps").asArray();
        List<DependencySpec> dependencySpecs = new ArrayList<>();
        deps.forEach(dep -> dependencySpecs.add(parseDependencySpec(dep.asObject(), defaultSource)));

        JsonObject ext = HJsonUtil.contains(object, "ext") ?
                object.get("ext").asObject() :
                new JsonObject();

        return new ModSpec(
                id,
                name,
                desc,
                authors,
                version,
                gameVersion,
                url,
                dependencySpecs,
                JsonObject.unmodifiableObject(ext)
        );
    }

    public DependencySpec parseDependencySpec(JsonObject object, String defaultSource) {
        String id = HJsonUtil.getOrThrow(object, "id").asString();
        String version = HJsonUtil.getOrThrow(object, "version").asString();
        String source = object.getString("source", defaultSource);
        return new DependencySpec(id, version, source);
    }
}
