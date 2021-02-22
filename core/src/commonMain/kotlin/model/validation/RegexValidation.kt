package com.wcisang.kotlinmultiplatform.model.validation

import com.wcisang.annotations.PagValidation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("validation:regex")
@PagValidation
data class RegexValidation(val data: Data) : InputValidation(){

    @Serializable
    data class Data(val regex: String, val message: String)

    override fun validate(text: String): Boolean {
        return data.regex.toRegex().matches(text)
    }

    override fun getErrorMessage(): String {
        return data.message
    }
}