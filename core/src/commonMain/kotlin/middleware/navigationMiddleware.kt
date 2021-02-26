package com.wcisang.kotlinmultiplatform.middleware

import com.wcisang.kotlinmultiplatform.listeners.NavigationListener
import com.wcisang.kotlinmultiplatform.model.actionrow.NavigationAction
import com.wcisang.kotlinmultiplatform.model.actionrow.OpenNewScreenActionRow
import com.wcisang.kotlinmultiplatform.state.AppState
import org.reduxkotlin.middleware

fun navigationMiddleware(navigationListener: NavigationListener) = middleware<AppState> { store, next, action ->
    if (action is NavigationAction) {
        if (action is OpenNewScreenActionRow) {
            handleOpenNewScreenActionRow(action, navigationListener)
        }
    }
    val result = next(action)
    result
}

private fun handleOpenNewScreenActionRow(
    action: OpenNewScreenActionRow,
    navigationListener: NavigationListener
) {
    navigationListener.goTo(action.data.nextScreen)
}
