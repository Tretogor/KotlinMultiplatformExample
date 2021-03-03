package com.wcisang.kotlinmultiplatform.middleware

import com.wcisang.kotlinmultiplatform.listeners.FrameworkListener
import com.wcisang.kotlinmultiplatform.model.actionrow.FrameworkAction
import com.wcisang.kotlinmultiplatform.model.actionrow.OpenUrlActionRow
import com.wcisang.kotlinmultiplatform.model.actionrow.PhoneCallActionRow
import com.wcisang.kotlinmultiplatform.state.AppState
import com.wcisang.kotlinmultiplatform.status.InformationStatus
import com.wcisang.kotlinmultiplatform.util.buildUrl
import org.reduxkotlin.Store
import org.reduxkotlin.middleware

internal fun frameworkMiddleware(
    frameworkListener: FrameworkListener
) = middleware<AppState> { store, next, action ->
    if (action is FrameworkAction) {
        if (action is OpenUrlActionRow) {
            handleOpenUrlActionRow(frameworkListener, store, action)
        }
        if (action is PhoneCallActionRow) {
            handlePhoneCallActionRow(frameworkListener, store, action)
        }
    }

    val result = next(action)
    result
}

private fun handlePhoneCallActionRow(
    frameworkListener: FrameworkListener,
    store: Store<AppState>,
    action: PhoneCallActionRow
) {
    if (information is InformationStatus.Success) {
        frameworkListener.onPhoneCall(
            (information as InformationStatus.Success).results.first() as String
        )
    }
    information = InformationStatus.Holding
}

private fun handleOpenUrlActionRow(
    frameworkListener: FrameworkListener,
    store: Store<AppState>,
    action: OpenUrlActionRow
) {
    if (information is InformationStatus.Success) {
        frameworkListener.onOpenUrl(
            action.data.url.buildUrl(
                action.data.querys,
                (information as InformationStatus.Success).results as MutableList<String>
            )
        )
    }
    information = InformationStatus.Holding
}

