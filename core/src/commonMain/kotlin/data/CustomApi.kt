package com.wcisang.kotlinmultiplatform.data

import com.wcisang.kotlinmultiplatform.model.Screen
import com.wcisang.kotlinmultiplatform.model.buildPolymorphicRow
import com.wcisang.kotlinmultiplatform.model.actionrow.buildPolymorphicAction
import com.wcisang.kotlinmultiplatform.model.validation.buildPolymorphicValidation
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

class CustomApi {

    private val baseUrl = "https://private-5b915e-poctretogor.apiary-mock.com"

    private val module = SerializersModule {
        buildPolymorphicRow()
        buildPolymorphicAction()
        buildPolymorphicValidation()
    }

    private val nonStrictJson = Json {
        isLenient = true
        ignoreUnknownKeys = true
        serializersModule = module
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