package com.jetbrains.handson.mpp.mobile.model

import com.jetbrains.handson.mpp.mobile.model.validation.InputValidation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("row:input")
data class InputRow(val id: String, val data: Data, val validation: InputValidation) : Row() {

    @Serializable
    data class Data(val hint: String)
}