package io.github.crmodders.javacrm1.impl.v3;

import java.util.List;

import org.hjson.JsonObject;

import io.github.crmodders.javacrm1.api.IModVersionSpec;

public record V3ModVersionSpec(
    String version,
    String gameVersion,
    String url,
    List<V3DependencySpec> deps,
    JsonObject ext
) implements IModVersionSpec { }
