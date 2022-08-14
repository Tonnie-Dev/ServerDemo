import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    application
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {

    //we have our Ktor dependencies inside of maven
    mavenCentral()
}

//application block with the server engine to use
application{

    mainClass.set("com.uxstate.ApplicationKt")
}

dependencies {
    testImplementation(kotlin("test"))

    //Core dependencies - basic functionality for Ktor Server
    implementation("io.ktor:ktor-server-core:2.0.3")
    implementation("io.ktor:ktor-server-netty:2.0.3")

    //content-negotiation plugin
    implementation("io.ktor:ktor-server-content-negotiation:2.0.3")


    //Logging dependency - see logs from the server
    implementation("ch.qos.logback:logback-classic:1.2.11")

    implementation("io.ktor:ktor-serialization:2.0.3")

    //for @Serializable annotation to work
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0-RC")

    //for json() to work
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3")

    //html builder
    //implementation("io.ktor:ktor-html-builder:2.0.3")
    implementation("io.ktor:ktor-server-html-builder:2.0.3")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}