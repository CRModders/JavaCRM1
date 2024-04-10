package io.github.crmodders.javacrm1.impl.v1;

import java.util.List;

import io.github.crmodders.javacrm1.api.IRepoSpec;

public record V1RepoSpec(
        int specVersion,
        String rootId,
        int lastUpdated,
        List<V1ModSpec> mods
) implements IRepoSpec { }
