package io.github.crmodders.javacrm1;

import io.github.crmodders.javacrm1.impl.v1.V1DependencySpec;
import io.github.crmodders.javacrm1.impl.v1.V1ModSpec;
import io.github.crmodders.javacrm1.impl.v1.V1Parser;
import io.github.crmodders.javacrm1.impl.v1.V1RepoSpec;

import org.hjson.JsonObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestV1Parser {

    public static final String TEST_SPEC = """
            {
                specVersion: 1
                rootId: io.github.crmodders
                lastUpdated: 0
                mods: [
                    {
                        id: io.github.crmodders.wiremod
                        name: WireMod
                        desc: Mod that adds wiring to Cosmic Reach.
                        authors: [ "Hellscaped" ]
                        version: 0.6.9
                        gameVersion: 0.1.7
                        url: https://example.com/WireMod-0.6.9.jar
                        deps: [
                            {
                                id: io.github.crmodders.wirelib
                                version: 4.2.0
                            }
                        ]
                        ext: {
                            icon: https://example.com/WireMod.png
                        }
                    }
                    {
                        id: io.github.crmodders.wirelib
                        name: WireLib
                        desc: Shared code mod for wiring primarily used in WireMod.
                        authors: [ "Hellscaped" ]
                        version: 4.2.0
                        gameVersion: 0.1.7
                        url: https://example.com/WireLib-4.2.0.jar
                        deps: [ ]
                        ext: { }
                    }
                ]
            }
            """;

    V1RepoSpec parse() {
        V1Parser parser = new V1Parser();
        return parser.parse(TEST_SPEC);
    }

    @Test
    @DisplayName("Test V1 parser - parse")
    void testRoot() {
        var repo = parse();

        assertEquals(1, repo.specVersion(), "repo.specVersion");
        assertEquals("io.github.crmodders", repo.rootId(), "repo.rootId");
        assertEquals(0, repo.lastUpdated(), "repo.lastUpdated");
    }

    @Test
    @DisplayName("Test V1 parser - first mod")
    void testFirstMod() {
        var repo = parse();

        V1ModSpec firstMod = repo.mods().get(0);
        assertEquals("io.github.crmodders.wiremod", firstMod.id(), "firstMod.id");
        assertEquals("WireMod", firstMod.name(), "firstMod.name");
        assertEquals("Mod that adds wiring to Cosmic Reach.", firstMod.desc(), "firstMod.desc");
        assertEquals(List.of("Hellscaped"), firstMod.authors(), "firstMod.authors");
        assertEquals("0.6.9", firstMod.version(), "firstMod.version");
        assertEquals("0.1.7", firstMod.gameVersion(), "firstMod.gameVersion");
        assertEquals("https://example.com/WireMod-0.6.9.jar", firstMod.url(), "firstMod.url");
        assertEquals(List.of(new V1DependencySpec("io.github.crmodders.wirelib", "4.2.0", "io.github.crmodders")),
                firstMod.deps(), "firstMod.deps");
        JsonObject ext = new JsonObject();
        ext.add("icon", "https://example.com/WireMod.png");
        assertEquals(ext, firstMod.ext(), "firstMod.ext");
    }

    @Test
    @DisplayName("Test V1 parser - second mod")
    void testSecondMod() {
        var repo = parse();

        V1ModSpec secondMod = repo.mods().get(1);
        assertEquals("io.github.crmodders.wirelib", secondMod.id(), "secondMod.id");
        assertEquals("WireLib", secondMod.name(), "secondMod.name");
        assertEquals("Shared code mod for wiring primarily used in WireMod.", secondMod.desc(), "secondMod.desc");
        assertEquals(List.of("Hellscaped"), secondMod.authors(), "secondMod.authors");
        assertEquals("4.2.0", secondMod.version(), "secondMod.version");
        assertEquals("0.1.7", secondMod.gameVersion(), "secondMod.gameVersion");
        assertEquals("https://example.com/WireLib-4.2.0.jar", secondMod.url(), "secondMod.url");
        assertEquals(List.of(), secondMod.deps(), "secondMod.deps");
        assertEquals(new JsonObject(), secondMod.ext(), "secondMod.ext");
    }

}
