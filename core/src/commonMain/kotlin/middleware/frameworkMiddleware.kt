package com.jetbrains.handson.mpp.mobile.middleware

import com.jetbrains.handson.mpp.mobile.listeners.FrameworkListener
import com.jetbrains.handson.mpp.mobile.model.actionrow.FrameworkAction
import com.jetbrains.handson.mpp.mobile.model.actionrow.OpenUrlActionRow
import com.jetbrains.handson.mpp.mobile.state.AppState
import com.jetbrains.handson.mpp.mobile.state.ui.GetViewInformation
import com.jetbrains.handson.mpp.mobile.state.ui.InvalidInputInformation
import com.jetbrains.handson.mpp.mobile.state.ui.SendViewInformation
import com.jetbrains.handson.mpp.mobile.status.InformationStatus
import com.jetbrains.handson.mpp.mobile.util.buildUrl
import org.reduxkotlin.middleware

private var information: InformationStatus = InformationStatus.Holding

fun frameworkMiddleware(
    frameworkListener: FrameworkListener
) = middleware<AppState> { store, next, action ->
    if (action is FrameworkAction) {
        if (action is OpenUrlActionRow) {
            store.dispatch(GetViewInformation(action.data.query.from))
            if (information is InformationStatus.Success) {
                frameworkListener.onOpenUrl(
                    action.data.url.buildUrl(
                        action.data.query.name,
                        (information as? InformationStatus.Success)?.data as String
                    )
                )
                information = InformationStatus.Holding
            }
        }
    }

    if (action is SendViewInformation) {
        information = if (action.validation != null) {
            if (action.validation.validate(action.data)) {
                InformationStatus.Success(action.data)
            } else {
                store.dispatch(InvalidInputInformation(action.validation.getErrorMessage()))
                InformationStatus.Canceled
            }
        } else {
            InformationStatus.Success(action.data)
        }

    }
    val result = next(action)
    result
}