plugins {
    java
    id("meteordevelopment.meteor-gradle") version "0.2.2"
}

repositories {
    mavenCentral()
    maven {
        name = "meteor-maven"
        url = uri("https://maven.meteorclient.com/releases")
    }
    maven {
        name = "meteor-maven-snapshots"
        url = uri("https://maven.meteorclient.com/snapshots")
    }
}

dependencies {
    implementation("meteordevelopment:meteor-client:0.5.8")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
