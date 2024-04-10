package io.github.crmodders.javacrm1;

import io.github.crmodders.javacrm1.impl.v3.V3DependencySpec;
import io.github.crmodders.javacrm1.impl.v3.V3ModSpec;
import io.github.crmodders.javacrm1.impl.v3.V3ModVersionSpec;
import io.github.crmodders.javacrm1.impl.v3.V3Parser;
import io.github.crmodders.javacrm1.impl.v3.V3RepoSpec;

import org.hjson.JsonObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestV3Parser {

    public static final String TEST_SPEC = """
            {
                specVersion: 3
                rootId: io.github.crmodders
                lastUpdated: 0
                deps: [
                    "https://example.com/repo.hjson"
                ]
                mods: [
                    {
                        id: io.github.crmodders.wiremod
                        name: WireMod
                        desc: Mod that adds wiring to Cosmic Reach.
                        authors: [ "Hellscaped" ]
                        latest: 0.0.2
                        versions: [
                            {
                                version: 0.0.2
                                gameVersion: 0.1.7
                                url: https://example.com/WireMod-0.0.2.jar
                                ext: { }
                                deps: [
                                    {
                                        id: io.github.crmodders.wirelib
                                        version: 4.2.1
                                        source: io.github.crmodders
                                    }
                                ]
                            }
                            {
                                version: 0.0.1
                                gameVersion: 0.1.7
                                url: https://example.com/WireMod-0.0.1.jar
                                ext: { }
                                deps: [
                                    {
                                        id: io.github.crmodders.wirelib
                                        version: 4.2.0
                                        source: io.github.crmodders
                                    }
                                ]
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
                        latest: 4.2.0
                        versions: [
                            {
                                version: 4.2.0
                                gameVersion: 0.1.7
                                url: https://example.com/WireLib-4.2.0.jar
                                ext: { }
                                deps: [ ]
                            }
                        ]
                        ext: { }
                    }
                ]
            }
            """;

    V3RepoSpec parse() {
        V3Parser parser = new V3Parser();
        return parser.parse(TEST_SPEC);
    }

    @Test
    @DisplayName("Test V3 parser - root")
    void testRoot() {
        var repo = parse();

        assertEquals(3, repo.specVersion(), "repo.specVersion");
        assertEquals("io.github.crmodders", repo.rootId(), "repo.rootId");
        assertEquals(0, repo.lastUpdated(), "repo.lastUpdated");
        assertEquals(List.of("https://example.com/repo.hjson"), repo.deps(), "repo.deps");
    }

    @Test
    @DisplayName("Test V3 parser - first mod")
    void testFirstMod() {
        var repo = parse();

        V3ModSpec firstMod = repo.mods().get(0);
        assertEquals("io.github.crmodders.wiremod", firstMod.id(), "firstMod.id");
        assertEquals("WireMod", firstMod.name(), "firstMod.name");
        assertEquals("Mod that adds wiring to Cosmic Reach.", firstMod.desc(), "firstMod.desc");
        assertEquals(List.of("Hellscaped"), firstMod.authors(), "firstMod.authors");
        assertEquals("0.0.2", firstMod.latest(), "firstMod.latest");

        V3ModVersionSpec firstVersion = firstMod.versions().get(0);
        assertEquals("0.0.2", firstVersion.version(), "firstMod.firstVersion.version");
        assertEquals("0.1.7", firstVersion.gameVersion(), "firstMod.firstVersion.gameVersion");
        assertEquals("https://example.com/WireMod-0.0.2.jar", firstVersion.url(), "firstMod.firstVersion.url");
        assertEquals(List.of(new V3DependencySpec("io.github.crmodders.wirelib", "4.2.1", "io.github.crmodders")),
                firstVersion.deps(), "firstMod.firstVersion.deps");
        assertEquals(new JsonObject(), firstVersion.ext(), "firstMod.firstVersion.ext");

        V3ModVersionSpec secondVersion = firstMod.versions().get(1);
        assertEquals("0.0.1", secondVersion.version(), "firstMod.secondVersion.version");
        assertEquals("0.1.7", secondVersion.gameVersion(), "firstMod.secondVersion.gameVersion");
        assertEquals("https://example.com/WireMod-0.0.1.jar", secondVersion.url(), "firstMod.secondVersion.url");
        assertEquals(List.of(new V3DependencySpec("io.github.crmodders.wirelib", "4.2.0", "io.github.crmodders")),
                secondVersion.deps(), "firstMod.secondVersion.deps");
        assertEquals(new JsonObject(), secondVersion.ext(), "firstMod.secondVersion.ext");
    }

    @Test
    @DisplayName("Test V3 parser - second mod")
    void testSecondMod() {
        var repo = parse();

        V3ModSpec secondMod = repo.mods().get(1);
        assertEquals("io.github.crmodders.wirelib", secondMod.id(), "secondMod.id");
        assertEquals("WireLib", secondMod.name(), "secondMod.name");
        assertEquals("Shared code mod for wiring primarily used in WireMod.", secondMod.desc(), "secondMod.desc");
        assertEquals(List.of("Hellscaped"), secondMod.authors(), "secondMod.authors");
        assertEquals("4.2.0", secondMod.latest(), "secondMod.latest");

        V3ModVersionSpec firstVersion = secondMod.versions().get(0);
        assertEquals("4.2.0", firstVersion.version(), "secondMod.firstVersion.version");
        assertEquals("0.1.7", firstVersion.gameVersion(), "secondMod.firstVersion.gameVersion");
        assertEquals("https://example.com/WireLib-4.2.0.jar", firstVersion.url(), "secondMod.firstVersion.url");
        assertEquals(List.of(), firstVersion.deps(), "secondMod.firstVersion.deps");
        assertEquals(new JsonObject(), firstVersion.ext(), "secondMod.firstVersion.ext");
    }
}
