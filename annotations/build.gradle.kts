plugins {
    kotlin("multiplatform")
    id("java-library")
}

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    jvm() // Creates a JVM target with the default name 'jvm'

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.KOTLIN_STD_COMMON)
            }
        }

    }
}