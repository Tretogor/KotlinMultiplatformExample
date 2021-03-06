package com.wcisang.kotlinmultiplatform.model.actionrow

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
abstract class ActionRow {

    abstract fun hasInformationToFill() : Boolean
    abstract fun getIdsForSearch() : List<String>
}