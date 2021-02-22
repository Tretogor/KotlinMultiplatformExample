package com.wcisang.kotlinmultiplatform.model.actionrow

import com.wcisang.annotations.PagAction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("action:openUrl")
@PagAction
data class OpenUrlActionRow(val data: Data) : ActionRow(), FrameworkAction {

    @Serializable
    data class Data(val url: String, val query: Query)

    @Serializable
    data class Query(val name: String, val from: String, var value: String = "")
}