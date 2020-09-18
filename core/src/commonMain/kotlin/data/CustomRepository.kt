package com.jetbrains.handson.mpp.mobile.data

import com.jetbrains.handson.mpp.mobile.model.Screen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class CustomRepository : KoinComponent {

    private val api : CustomApi by inject()

    suspend fun getRows() = api.getRows().rows

}