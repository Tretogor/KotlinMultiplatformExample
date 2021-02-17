package com.jetbrains.handson.mpp.mobile.model.validation

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
abstract class InputValidation {
    abstract fun validate(data: String): Boolean
    abstract fun getErrorMessage(): String
}