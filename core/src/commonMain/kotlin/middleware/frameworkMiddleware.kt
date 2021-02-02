package com.jetbrains.handson.mpp.mobile.middleware

import com.jetbrains.handson.mpp.mobile.listeners.FrameworkListener
import com.jetbrains.handson.mpp.mobile.model.actionrow.FrameworkAction
import com.jetbrains.handson.mpp.mobile.model.actionrow.OpenUrlActionRow
import com.jetbrains.handson.mpp.mobile.state.AppState
import com.jetbrains.handson.mpp.mobile.state.ui.GetViewInformation
import com.jetbrains.handson.mpp.mobile.state.ui.SendViewInformation
import com.jetbrains.handson.mpp.mobile.util.buildUrl
import org.reduxkotlin.middleware

private var information : String = ""

fun frameworkMiddleware(
    frameworkListener: FrameworkListener
) = middleware<AppState> { store, next, action ->
    if (action is FrameworkAction) {
        if (action is OpenUrlActionRow) {
            store.dispatch(GetViewInformation(action.data.query.from))
            frameworkListener.onOpenUrl(action.data.url.buildUrl(action.data.query.name, information))
        }
    }
    if (action is SendViewInformation) {
        information = action.data
    }
    val result = next(action)
    result
}