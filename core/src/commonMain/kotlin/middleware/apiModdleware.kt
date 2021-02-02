package com.jetbrains.handson.mpp.mobile.middleware

import com.jetbrains.handson.mpp.mobile.model.actionrow.FrameworkAction
import com.jetbrains.handson.mpp.mobile.state.AppState
import org.reduxkotlin.middleware

internal fun apiMiddleware(

) = middleware<AppState> { store, next, action ->
    val result = next(action)

    result
}