package com.wcisang.kotlinmultiplatform.middleware

import com.wcisang.kotlinmultiplatform.model.actionrow.ActionRow
import com.wcisang.kotlinmultiplatform.state.AppState
import com.wcisang.kotlinmultiplatform.state.ui.GetViewInformation
import com.wcisang.kotlinmultiplatform.state.ui.InvalidInputInformation
import com.wcisang.kotlinmultiplatform.state.ui.SendViewInformation
import com.wcisang.kotlinmultiplatform.status.InformationStatus
import org.reduxkotlin.Store
import org.reduxkotlin.middleware

var information: InformationStatus = InformationStatus.Holding

internal fun informationCollectorMiddleware() = middleware<AppState> { store, next, action ->
    when (action) {
        is ActionRow -> {
            if (action.hasInformationToFill()) {
                action.getIdsForSearch().forEach {
                    store.dispatch(GetViewInformation(it))
                }
            }
            val result = next(action)
            result
        }
        is SendViewInformation -> {
            handleResult(store, action)
            AppState()
        }
        else -> {
            val result = next(action)
            result
        }
    }
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