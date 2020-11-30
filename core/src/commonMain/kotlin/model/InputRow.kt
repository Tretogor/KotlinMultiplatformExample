package com.jetbrains.handson.mpp.mobile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("row:input")
class InputRow(val id: String, val data: Data) : Row() {

    @Serializable
    data class Data(val hint: String)
}