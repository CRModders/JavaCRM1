package io.github.crmodders.javacrm1.impl.v2;

import org.hjson.JsonObject;

import io.github.crmodders.javacrm1.api.IModSpec;

import java.util.List;

public record V2ModSpec(
        String id,
        String name,
        String desc,
        List<String> authors,
        String version,
        String gameVersion,
        String url,
        List<V2DependencySpec> deps,
        JsonObject ext
) implements IModSpec { }
