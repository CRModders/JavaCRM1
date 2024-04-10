package io.github.crmodders.javacrm1.impl.v2;

import io.github.crmodders.javacrm1.api.IDependencySpec;

public record V2DependencySpec(
        String id,
        String version,
        String source
) implements IDependencySpec { }
