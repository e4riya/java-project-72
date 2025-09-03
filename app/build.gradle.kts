plugins {
    id("java")
    application
    checkstyle
    jacoco
    id("org.sonarqube") version "6.3.1.5724"
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