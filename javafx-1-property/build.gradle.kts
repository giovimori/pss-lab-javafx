plugins {
    java
    application
}

repositories {
    mavenCentral()
}

val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    "swing",
    "graphics"
)

// !!! PUNTO CRUCIALE 1 !!!
// Rimuovi "mac" (che è per Intel) e metti "mac-aarch64" (per il tuo M1/M2/M3)
val supportedPlatforms = listOf("mac-aarch64", "linux", "win")

dependencies {
    // Suppressions for SpotBugs
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.9.8")

    // !!! PUNTO CRUCIALE 2 !!!
    // Usa la versione 17.0.8 (la 15 è troppo vecchia per il tuo Mac)
    val javaFxVersion = "17.0.8"

    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }

    // Aggiornamento JUnit
    val jUnitVersion = "5.10.0"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClass.set("it.unibo.javafx.property.App\$Main")
}