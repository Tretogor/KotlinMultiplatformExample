import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("kapt")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
}

kotlin {

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
                implementation(Dependencies.KOTLIN_STD_COMMON)
                implementation(Dependencies.KOIN_CORE)
                implementation(project(":annotations"))
                configurations.get("kapt").dependencies.add(
                    project(":processor")
                )

                implementation("com.chrynan.parcelable:parcelable-android:0.2.0")
                implementation(Dependencies.COROUTINES_CORE)

                implementation(Dependencies.KTOR_CORE)
                implementation(Dependencies.KTOR_JSON)
//                implementation(Dependencies.KTOR_LOGGING)
                implementation(Dependencies.KTOR_SERIALIZATION)

                implementation(Dependencies.KOTLINX_SERIALIZATION)

                implementation(Dependencies.REDUX)
                //implementation(Dependencies.REDUX_THUNK)
                kotlin.srcDir("${buildDir.absolutePath}/generated/source/kaptKotlin/")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Dependencies.KOTLIN_STD)

                implementation(Dependencies.COROUTINES_ANDROID)
                implementation(Dependencies.COROUTINES_CORE)

                implementation(Dependencies.KTOR_ANDROID)

                implementation(Dependencies.SLF4J)
            }
        }

        val iosMain by getting {
            dependencies {
//                implementation(Dependencies.COROUTINES_CORE_NATIVE)

                implementation(Dependencies.KTOR_IOS)
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