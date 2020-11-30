package com.jetbrains.handson.mpp.mobile.model.actionrow

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("action:url")
class UrlActionRow(val data: Data) : ActionRow() {

    @Serializable
    data class Data(val url: String, val query: Query)

    @Serializable
    data class Query(val name: String, val from: String, var value: String = "")
}