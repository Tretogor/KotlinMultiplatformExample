package com.wcisang.kotlinmultiplatform.middleware

import com.wcisang.kotlinmultiplatform.state.AppState
import org.reduxkotlin.middleware

internal fun logginMiddleware() = middleware<AppState> { store, next, action ->
    val result = next(action)
    println("DISPATCH action: ${action::class.simpleName}: $action")
    println("next state: ${store.state}")
    result
}