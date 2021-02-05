package com.jetbrains.handson.mpp.mobile.model.validation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("validation:regex")
data class RegexValidation(val data: Data) : InputValidation(){

    @Serializable
    data class Data(val regex: String, val message: String)
}