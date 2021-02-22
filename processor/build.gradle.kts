plugins {
    kotlin("multiplatform")
    kotlin("kapt")
    id("java-library")
}

repositories {
    mavenCentral()
}

kotlin {

    jvm() //this is only used as kapt (annotation processor, so pure jvm)

    sourceSets {

        val jvmMain by getting {
            dependencies {
                implementation(project(":annotations"))
                //code generation
                val kotlinpoetVersion = "1.7.2"
                implementation("com.squareup:kotlinpoet:$kotlinpoetVersion")
                implementation("com.squareup:kotlinpoet-metadata:$kotlinpoetVersion")
                implementation("com.squareup:kotlinpoet-metadata-specs:$kotlinpoetVersion")
                implementation("com.squareup:kotlinpoet-classinspector-elements:$kotlinpoetVersion")
                implementation("com.google.auto.service:auto-service:1.0-rc4")
                implementation(Dependencies.KOTLINX_SERIALIZATION)
                configurations.get("kapt").dependencies.add(
                    org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency(
                        "com.google.auto.service", "auto-service", "1.0-rc4"
                    )
                )
            }
        }

    }
}