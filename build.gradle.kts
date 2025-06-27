plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
}

group = "dev.demo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    implementation("org.junit.jupiter:junit-jupiter")
    implementation("org.junit.platform:junit-platform-suite-engine:1.13.1")

    // selenium
    implementation("org.seleniumhq.selenium:selenium-java:4.33.0")

    //log
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("ch.qos.logback:logback-classic:1.4.8")

    // Jackson Core for general functionality
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

    // Jackson CSV for CSV processing
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.15.2")
}

tasks.test {
    useJUnitPlatform()
    systemProperties = System.getProperties() as Map<String, Any?>
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}