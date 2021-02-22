package com.wcisang.kotlinmultiplatform.model

import com.wcisang.kotlinmultiplatform.model.validation.InputValidation
import com.wcisang.annotations.PagRow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("row:input")
@PagRow
data class InputRow(val id: String, val data: Data, val validation: InputValidation) : Row() {

    @Serializable
    data class Data(val hint: String)
}