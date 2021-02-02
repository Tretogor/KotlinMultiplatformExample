object Version {
    const val KOIN_VERSION = "3.0.1-alpha-2"
    const val COROUTINES_VERSION = "1.3.9-native-mt"
    const val KTOR_VERSION = "1.4.0"
    const val KOTLINX_SERIALIZATION_VERSION = "1.0.0-RC"
    const val SLF4J_VERSION = "1.7.28"
    const val REDUX_VERSION = "0.5.5"
    const val REDUX_THUNK_VERSION = "0.5.3"
}

object Dependencies {
    const val KOTLIN_STD_COMMON = "org.jetbrains.kotlin:kotlin-stdlib-common"
    const val KOTLIN_STD = "org.jetbrains.kotlin:kotlin-stdlib"
    const val KOIN_CORE = "org.koin:koin-core:${Version.KOIN_VERSION}"

    // Coroutines
    const val COROUTINES_CORE_COMMON = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Version.COROUTINES_VERSION}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES_VERSION}"
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES_VERSION}"
    const val COROUTINES_CORE_NATIVE = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Version.COROUTINES_VERSION}"

    // Ktor
    const val KTOR_CORE = "io.ktor:ktor-client-core:${Version.KTOR_VERSION}"
    const val KTOR_JSON = "io.ktor:ktor-client-json:${Version.KTOR_VERSION}"
    const val KTOR_LOGGING = "io.ktor:ktor-client-logging:${Version.KTOR_VERSION}"
    const val KTOR_SERIALIZATION = "io.ktor:ktor-client-serialization:${Version.KTOR_VERSION}"
    const val KTOR_ANDROID = "io.ktor:ktor-client-android:${Version.KTOR_VERSION}"
    const val KTOR_IOS = "io.ktor:ktor-client-ios:${Version.KTOR_VERSION}"

    const val SLF4J = "org.slf4j:slf4j-android:${Version.SLF4J_VERSION}"

    // Serialize
    const val KOTLINX_SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Version.KOTLINX_SERIALIZATION_VERSION}"

    // Redux
    const val REDUX = "org.reduxkotlin:redux-kotlin-threadsafe:${Version.REDUX_VERSION}"
    const val REDUX_THUNK = "org.reduxkotlin:redux-kotlin-thunk:${Version.REDUX_THUNK_VERSION}"
}