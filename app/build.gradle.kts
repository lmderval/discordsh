val jdaVersion = "6.3.0"

plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(libs.guava)
    implementation("net.dv8tion:JDA:${jdaVersion}") {
        exclude(module = "opus-java")
        exclude(module = "tink")
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "com.lmderval.discordsh.App"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
