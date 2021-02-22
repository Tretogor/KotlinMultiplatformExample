package com.wcisang.kotlinmultiplatform.middleware

import com.wcisang.kotlinmultiplatform.listeners.FrameworkListener
import com.wcisang.kotlinmultiplatform.model.actionrow.FrameworkAction
import com.wcisang.kotlinmultiplatform.model.actionrow.OpenUrlActionRow
import com.wcisang.kotlinmultiplatform.state.AppState
import com.wcisang.kotlinmultiplatform.state.ui.GetViewInformation
import com.wcisang.kotlinmultiplatform.state.ui.InvalidInputInformation
import com.wcisang.kotlinmultiplatform.state.ui.SendViewInformation
import com.wcisang.kotlinmultiplatform.status.InformationStatus
import com.wcisang.kotlinmultiplatform.util.buildUrl
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