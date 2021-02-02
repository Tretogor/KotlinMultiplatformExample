package com.jetbrains.handson.mpp.mobile.reducer

import com.jetbrains.handson.mpp.mobile.state.AppState
import com.jetbrains.handson.mpp.mobile.state.ui.GetViewInformation
import com.jetbrains.handson.mpp.mobile.state.ui.GetViewInformationState
import org.reduxkotlin.Reducer

val reducer: Reducer<AppState> = { state, action ->
    var state = state
    if (action is GetViewInformation) {
        state = GetViewInformationState(action.id)
    }else {
        state = AppState()
    }
    state
}