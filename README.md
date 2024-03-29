# JavaCRM-1

> A Java implementation and API for CRM-1

## Usage

```java
V1Parser parser = new V1Parser();
RepoSpec repo = parser.parse("""
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
""");
```

## Getting Started

Add this to your `settings.gradle(.kts)`:

```groovy
// groovy
sourceControl {
    gitRepository("https://github.com/CRModders/JavaCRM1.git") {
        producesModule("io.github.crmodders.javacrm1:JavaCRM1")
    }
}
```

```kotlin
// kotlin
import java.net.URI

sourceControl {
    gitRepository(URI("https://github.com/CRModders/JavaCRM1.git")) {
        producesModule("io.github.crmodders.javacrm1:JavaCRM1")
    }
}
```

...And in your `build.gradle(.kts)`:

```groovy
// groovy
dependencies {
    implementation "io.github.crmodders.javacrm1:JavaCRM1:v1.0.1"
}
```

```kotlin
// kotlin
dependencies {
    implementation("io.github.crmodders.javacrm1:JavaCRM1:v1.0.1")
}
```
