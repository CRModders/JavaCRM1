package io.github.crmodders.javacrm1.impl.v1;

import io.github.crmodders.javacrm1.api.IDependencySpec;

public record V1DependencySpec(
        String id,
        String version,
        String source
) implements IDependencySpec { }
