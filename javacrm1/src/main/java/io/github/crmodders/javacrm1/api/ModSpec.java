package io.github.crmodders.javacrm1.api;

import org.hjson.JsonObject;

import java.util.List;

public record ModSpec(
        String id,
        String name,
        String desc,
        List<String> authors,
        String version,
        String gameVersion,
        String url,
        List<DependencySpec> deps,
        JsonObject ext
) { }
