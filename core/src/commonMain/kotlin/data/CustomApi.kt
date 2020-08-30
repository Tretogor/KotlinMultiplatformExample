package com.jetbrains.handson.mpp.mobile.data

import com.jetbrains.handson.mpp.mobile.model.ButtonRow
import com.jetbrains.handson.mpp.mobile.model.InputRow
import com.jetbrains.handson.mpp.mobile.model.Row
import com.jetbrains.handson.mpp.mobile.model.Screen
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

class CustomApi {

    private val baseUrl = "https://private-5b915e-poctretogor.apiary-mock.com"

    private val module = SerializersModule {
        polymorphic(Row::class, InputRow::class, InputRow.serializer())
        polymorphic(Row::class, ButtonRow::class, ButtonRow.serializer())
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
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    @Throws(Exception::class)
    suspend fun getRows() = client.get<Screen>("$baseUrl/screen")
}