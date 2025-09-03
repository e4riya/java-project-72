plugins {
    id("java")
    application
    checkstyle
    jacoco
    id("org.sonarqube") version "6.3.1.5724"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}
application{
    mainClass = "hexlet.code.App"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin:6.1.3")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation("io.javalin:javalin-rendering:6.1.3")
    implementation("com.h2database:h2:2.2.220")
    implementation("com.zaxxer:HikariCP:5.0.1")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
tasks.jacocoTestReport {reports{ xml.required.set(true) } }

tasks.test {
    useJUnitPlatform()
}
sonar {
    properties {
        property("sonar.projectKey", "e4riya_java-project-72")
        property("sonar.organization", "e4riya")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}