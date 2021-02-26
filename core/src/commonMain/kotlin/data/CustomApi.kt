package com.wcisang.kotlinmultiplatform.data

import com.wcisang.kotlinmultiplatform.model.Screen
import com.wcisang.kotlinmultiplatform.serializers.SerializerUtils
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class CustomApi {

    private val baseUrl = "https://private-5b915e-poctretogor.apiary-mock.com"

    private val nonStrictJson = Json {
        isLenient = true
        ignoreUnknownKeys = true
        serializersModule = SerializerUtils.module
    }

    private val client by lazy {
        HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer(nonStrictJson)
            }
        }
    }

    @Throws(Exception::class)
    suspend fun getRows() = client.get<Screen>("$baseUrl/screen")

    @Throws(Exception::class)
    suspend fun makeCall() = client.get<Screen>("$baseUrl/screen")
}