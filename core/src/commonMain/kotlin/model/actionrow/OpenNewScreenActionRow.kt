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
    data class Data(val nextScreen: Screen)
}