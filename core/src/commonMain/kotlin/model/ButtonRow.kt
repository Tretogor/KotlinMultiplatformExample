package com.jetbrains.handson.mpp.mobile.model

import com.jetbrains.handson.mpp.mobile.model.actionrow.ActionRow
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("row:button")
class ButtonRow(val id: String, val data: Data, val action: ActionRow) : Row() {

    @Serializable
    data class Data(val text: String)
}