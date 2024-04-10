package io.github.crmodders.javacrm1.impl.v1;

import org.hjson.JsonObject;

import io.github.crmodders.javacrm1.api.IModSpec;

import java.util.List;

public record V1ModSpec(
        String id,
        String name,
        String desc,
        List<String> authors,
        String version,
        String gameVersion,
        String url,
        List<V1DependencySpec> deps,
        JsonObject ext
) implements IModSpec { }
