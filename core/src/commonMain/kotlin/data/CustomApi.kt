package com.jetbrains.handson.mpp.mobile.data

import com.jetbrains.handson.mpp.mobile.model.ButtonRow
import com.jetbrains.handson.mpp.mobile.model.InputRow
import com.jetbrains.handson.mpp.mobile.model.Row
import com.jetbrains.handson.mpp.mobile.model.Screen
import com.jetbrains.handson.mpp.mobile.model.actionrow.ActionRow
import com.jetbrains.handson.mpp.mobile.model.actionrow.OpenUrlActionRow
import com.jetbrains.handson.mpp.mobile.model.validation.InputValidation
import com.jetbrains.handson.mpp.mobile.model.validation.RegexValidation
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuilder

class CustomApi {

    private val baseUrl = "https://private-5b915e-poctretogor.apiary-mock.com"

    private val module = SerializersModule {
        builderPolymorphicRow()
        builderPolymorphicActionRow()
        builderPolymorphicValidation()
    }

    private fun SerializersModuleBuilder.builderPolymorphicRow() {
        polymorphic(Row::class, InputRow::class, InputRow.serializer())
        polymorphic(Row::class, ButtonRow::class, ButtonRow.serializer())
    }

    private fun SerializersModuleBuilder.builderPolymorphicActionRow() {
        polymorphic(ActionRow::class, OpenUrlActionRow::class, OpenUrlActionRow.serializer())
    }

    private fun SerializersModuleBuilder.builderPolymorphicValidation() {
        polymorphic(InputValidation::class, RegexValidation::class, RegexValidation.serializer())
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