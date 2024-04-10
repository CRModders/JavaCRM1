package io.github.crmodders.javacrm1.impl.v3;

import java.util.List;

import io.github.crmodders.javacrm1.api.IRepoSpec;

public record V3RepoSpec(
        int specVersion,
        String rootId,
        int lastUpdated,
        List<String> deps,
        List<V3ModSpec> mods
) implements IRepoSpec { }
