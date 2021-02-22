package com.wcisang.kotlinmultiplatform.middleware

import com.wcisang.kotlinmultiplatform.model.actionrow.FrameworkAction
import com.wcisang.kotlinmultiplatform.state.AppState
import org.reduxkotlin.middleware

internal fun apiMiddleware(

) = middleware<AppState> { store, next, action ->
    val result = next(action)

    result
}