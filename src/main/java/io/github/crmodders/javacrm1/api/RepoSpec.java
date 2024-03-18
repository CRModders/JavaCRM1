package io.github.crmodders.javacrm1.api;

import java.util.List;

public record RepoSpec(
        int specVersion,
        String rootId,
        int lastUpdated,
        List<ModSpec> mods
) { }
