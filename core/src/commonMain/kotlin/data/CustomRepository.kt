package com.wcisang.kotlinmultiplatform.data

import com.wcisang.kotlinmultiplatform.model.Row
import org.koin.core.KoinComponent
import org.koin.core.inject

class CustomRepository : KoinComponent {

    private val api : CustomApi by inject()

    suspend fun getRows() : List<Row>{
        return api.getRows().rows
    }

    suspend fun makeCall() : List<Row> {
        return api.makeCall().rows
    }

}