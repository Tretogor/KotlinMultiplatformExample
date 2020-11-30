package com.jetbrains.handson.mpp.mobile.middleware

import com.jetbrains.handson.mpp.mobile.state.AppState
import kotlinx.coroutines.CoroutineDispatcher
import org.reduxkotlin.middleware

internal fun  logginMiddleware() = middleware<AppState> { store, next, action ->
    val result = next(action)
    println("DISPATCH action: ${action::class.simpleName}: $action")
    println("next state: ${store.state}")
    result
}