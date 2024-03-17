plugins {
    id("java")
}

group = "io.github.crmodders.javacrm1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.hjson:hjson:3.1.0")
}

tasks.test {
    useJUnitPlatform()
}
