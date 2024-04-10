package io.github.crmodders.javacrm1.impl.v3;

import io.github.crmodders.javacrm1.api.IDependencySpec;

public record V3DependencySpec(
        String id,
        String version,
        String source
) implements IDependencySpec { }
