import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
}

kotlin {
     val ktor_version = "1.4.0"
     val serialization_version = "1.0.0-RC"
     val slf4j_version = "1.7.28"
     val koin_version = "3.0.1-alpha-2"
     val coroutines_version = "1.3.0-RC"

    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "core"
            }
        }
    }

    jvm("android")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
                api("org.koin:koin-core:$koin_version")

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutines_version")

                // Ktor
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-json:$ktor_version")
                implementation("io.ktor:ktor-client-logging:$ktor_version")
                implementation("io.ktor:ktor-client-serialization:$ktor_version")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:${serialization_version}")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")

                // Ktor
                implementation("io.ktor:ktor-client-android:$ktor_version")
//                implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
//                implementation("io.ktor:ktor-client-json-jvm:$ktor_version")
//                implementation("io.ktor:ktor-client-logging-jvm:$ktor_version")
//                implementation("io.ktor:ktor-client-serialization-jvm:$ktor_version")
                implementation("org.slf4j:slf4j-android:$slf4j_version")

            }
        }

        val iosMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$coroutines_version")

                // Ktor
                implementation("io.ktor:ktor-client-ios:$ktor_version")
//                implementation("io.ktor:ktor-client-core-native:$ktor_version")
//                implementation("io.ktor:ktor-client-json-native:$ktor_version")
//                implementation("io.ktor:ktor-client-logging-native:$ktor_version")
//                implementation("io.ktor:ktor-client-serialization-native:$ktor_version")
            }
        }
    }
}

val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    /// selecting the right configuration for the iOS
    /// framework depending on the environment
    /// variables set by Xcode build
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets
        .getByName<KotlinNativeTarget>("ios")
        .binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    /// generate a helpful ./gradlew wrapper with embedded Java path
    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText("#!/bin/bash\n"
                + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                + "cd '${rootProject.rootDir}'\n"
                + "./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)