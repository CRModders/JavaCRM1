package io.github.crmodders.javacrm1.api;

public record DependencySpec(
        String id,
        String version,
        String source
) { }
