package io.github.crmodders.javacrm1.impl.v2;

import java.util.List;

import io.github.crmodders.javacrm1.api.IRepoSpec;

public record V2RepoSpec(
        int specVersion,
        String rootId,
        int lastUpdated,
        List<String> deps,
        List<V2ModSpec> mods
) implements IRepoSpec { }
