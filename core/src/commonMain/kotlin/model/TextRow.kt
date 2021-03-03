package com.wcisang.kotlinmultiplatform.model

import com.wcisang.annotations.PagRow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("row:text")
@PagRow
class TextRow(val id: String, val data: Data): Row() {

    @Serializable
    data class Data(val text: String)
}