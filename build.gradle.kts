import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    application
    kotlin("jvm") version "1.7.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {

    //we have our Ktor dependencies inside of maven
    mavenCentral()
}

//application block with the server engine to use
application{

    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    testImplementation(kotlin("test"))

    //Core dependencies - basic functionality for Ktor Server
    implementation("io.ktor:ktor-server-core:2.0.3")
    implementation("io.ktor:ktor-server-netty:2.0.3")

    //Logging dependency - see logs from the server
    implementation("ch.qos.logback:logback-classic:1.2.11")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}