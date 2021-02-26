package com.wcisang.kotlinmultiplatform.middleware

import com.wcisang.kotlinmultiplatform.listeners.FrameworkListener
import com.wcisang.kotlinmultiplatform.model.actionrow.FrameworkAction
import com.wcisang.kotlinmultiplatform.model.actionrow.OpenUrlActionRow
import com.wcisang.kotlinmultiplatform.model.actionrow.PhoneCallActionRow
import com.wcisang.kotlinmultiplatform.state.AppState
import com.wcisang.kotlinmultiplatform.state.ui.GetViewInformation
import com.wcisang.kotlinmultiplatform.state.ui.InvalidInputInformation
import com.wcisang.kotlinmultiplatform.state.ui.SendViewInformation
import com.wcisang.kotlinmultiplatform.status.InformationStatus
import com.wcisang.kotlinmultiplatform.util.buildUrl
import org.reduxkotlin.Store
import org.reduxkotlin.middleware

private var information: InformationStatus = InformationStatus.Holding

fun frameworkMiddleware(
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

    if (action is SendViewInformation) {
        handleResult(store, action)
    }

    val result = next(action)
    result
}

private fun handlePhoneCallActionRow(
    frameworkListener: FrameworkListener,
    store: Store<AppState>,
    action: PhoneCallActionRow
) {
    store.dispatch(GetViewInformation(action.data.query.from))
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
    action.data.getIdsFromQuery().forEach {
        store.dispatch(GetViewInformation(it))
    }
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

private fun handleResult(store: Store<AppState>, action: SendViewInformation) {
    if (action.validation != null) {
        if (action.validation.validate(action.data)) {
            setOrAddInformationToSuccess(action.data)
        } else {
            store.dispatch(InvalidInputInformation(action.validation.getErrorMessage(), action.id))
            information = InformationStatus.Canceled
        }
    } else {
        setOrAddInformationToSuccess(action.data)
    }
}

private fun setOrAddInformationToSuccess(data: String) {
    if (information is InformationStatus.Canceled) return

    if (information is InformationStatus.Success) {
        (information as InformationStatus.Success).addResult(data)
    } else {
        information = InformationStatus.Success().apply {
            addResult(data)
        }
    }
}