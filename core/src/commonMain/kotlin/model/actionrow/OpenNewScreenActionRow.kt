package com.wcisang.kotlinmultiplatform.model.actionrow

import com.wcisang.annotations.PagAction
import com.wcisang.kotlinmultiplatform.model.Screen
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("action:openNewScreen")
@PagAction
class OpenNewScreenActionRow(val data: Data): ActionRow(), NavigationAction {

    @Serializable
    data class Data(val nextScreen: Screen, val parameters: List<String>? = null)

    override fun hasInformationToFill(): Boolean {
        return !data.parameters.isNullOrEmpty()
    }

    override fun getIdsForSearch(): List<String> {
        return data.parameters ?: listOf()
    }
}