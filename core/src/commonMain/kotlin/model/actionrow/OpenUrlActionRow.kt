package com.wcisang.kotlinmultiplatform.model.actionrow

import com.wcisang.annotations.PagAction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("action:openUrl")
@PagAction
data class OpenUrlActionRow(val data: Data) : ActionRow(), FrameworkAction {

    @Serializable
    data class Data(val url: String, val querys: List<Query>) {
        fun getIdsFromQuery() : List<String>{
            return querys.map { it.from }
        }
    }

    @Serializable
    data class Query(val name: String, val from: String, var value: String = "")

    override fun hasInformationToFill(): Boolean {
        return data.querys.isNotEmpty()
    }

    override fun getIdsForSearch(): List<String> {
        return data.getIdsFromQuery()
    }
}