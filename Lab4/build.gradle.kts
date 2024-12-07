plugins {
    id("java")
}

group = "ru.aomikhailov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    //implementation ("org.json:json:20240303")
    //implementation("com.google.code.gson:gson:2.8.9")
    //implementation("javax.json:javax.json-api:1.0")
    implementation("org.glassfish:jakarta.json:2.0.1")
}

tasks.test {
    useJUnitPlatform()
}