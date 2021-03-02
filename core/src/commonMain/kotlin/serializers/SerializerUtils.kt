package com.wcisang.kotlinmultiplatform.serializers

import com.chrynan.parcelable.core.Parcelable
import com.wcisang.kotlinmultiplatform.model.actionrow.buildPolymorphicPagAction
import com.wcisang.kotlinmultiplatform.model.buildPolymorphicPagRow
import com.wcisang.kotlinmultiplatform.model.validation.buildPolymorphicPagValidation
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

object SerializerUtils {

    val module = SerializersModule {
        buildPolymorphicPagRow()
        buildPolymorphicPagAction()
        buildPolymorphicPagValidation()
    }

    val parcelable = Parcelable {
        serializersModule = module
    }

    val jsonSerialization = Json {
        isLenient = true
        ignoreUnknownKeys = true
        serializersModule = module
    }
}