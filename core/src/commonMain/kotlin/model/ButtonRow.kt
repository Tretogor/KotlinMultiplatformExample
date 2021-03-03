package com.wcisang.kotlinmultiplatform.model

import com.wcisang.kotlinmultiplatform.model.actionrow.ActionRow
import com.wcisang.annotations.PagRow
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("row:button")
@PagRow
data class ButtonRow(val id: String, val data: Data, val action: ActionRow) : Row() {

    @Serializable
    data class Data(val text: String)
}