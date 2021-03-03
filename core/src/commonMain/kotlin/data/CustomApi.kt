package com.wcisang.kotlinmultiplatform.data

import com.wcisang.kotlinmultiplatform.model.Screen
import com.wcisang.kotlinmultiplatform.serializers.SerializerUtils
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get

class CustomApi {

    private val baseUrl = "https://private-5b915e-poctretogor.apiary-mock.com"

    private val client by lazy {
        HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer(SerializerUtils.jsonSerialization)
            }
        }
    }

    @Throws(Exception::class)
    suspend fun getRows() = client.get<Screen>("$baseUrl/screen")

    @Throws(Exception::class)
    suspend fun makeGetCall() = client.get<Screen>("$baseUrl/phonecall")

    @Throws(Exception::class)
    suspend fun makeCall() = client.get<Screen>("$baseUrl/screen")
}