package io.github.crmodders.javacrm1.impl.v3;

import org.hjson.JsonObject;

import io.github.crmodders.javacrm1.api.IModSpec;

import java.util.List;

public record V3ModSpec(
        String id,
        String name,
        String desc,
        List<String> authors,
        String latest,
        List<V3ModVersionSpec> versions,
        JsonObject ext
) implements IModSpec { }
