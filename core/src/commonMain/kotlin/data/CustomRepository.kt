package com.jetbrains.handson.mpp.mobile.data

import com.jetbrains.handson.mpp.mobile.model.Row
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