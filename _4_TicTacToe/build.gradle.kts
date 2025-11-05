plugins {
    kotlin("jvm") version "2.2.10"
}

group = "isel.tds"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.okio:okio:3.15.0")

    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.25.1")
    implementation("org.mongodb:mongodb-driver-kotlin-sync:5.5.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}