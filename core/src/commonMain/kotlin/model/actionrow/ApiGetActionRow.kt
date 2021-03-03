package com.wcisang.kotlinmultiplatform.model.actionrow

import com.wcisang.annotations.PagAction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("action:apiGet")
@PagAction
class ApiGetActionRow(val data: Data): ActionRow(), ApiAction {

    @Serializable
    data class Data(val queries: List<Query>)

    @Serializable
    data class Query(val from: String, var value: String = "")

    override fun hasInformationToFill(): Boolean {
        return !data.queries.isNullOrEmpty()
    }

    override fun getIdsForSearch(): List<String> {
        return data.queries.map { it.from }
    }
}