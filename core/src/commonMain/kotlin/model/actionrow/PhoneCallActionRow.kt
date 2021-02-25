package com.wcisang.kotlinmultiplatform.model.actionrow

import com.wcisang.annotations.PagAction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("action:phoneCall")
@PagAction
class PhoneCallActionRow(val data: Data): ActionRow(), FrameworkAction {

    @Serializable
    data class Data(val query: Query)

    @Serializable
    data class Query(val from: String, var value: String = "")
}