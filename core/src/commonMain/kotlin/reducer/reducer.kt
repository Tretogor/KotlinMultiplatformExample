package com.jetbrains.handson.mpp.mobile.reducer

import com.jetbrains.handson.mpp.mobile.state.AppState
import com.jetbrains.handson.mpp.mobile.state.ui.GetViewInformation
import com.jetbrains.handson.mpp.mobile.state.ui.GetViewInformationState
import com.jetbrains.handson.mpp.mobile.state.ui.InvalidInputInformation
import com.jetbrains.handson.mpp.mobile.state.ui.InvalidInputState
import org.reduxkotlin.Reducer

val reducer: Reducer<AppState> = { state, action ->
    var state = state
    if (action is GetViewInformation) {
        state = GetViewInformationState(action.id)
    }else if (action is InvalidInputInformation){
       state = InvalidInputState(action.message)
    }else {
        state = AppState()
    }
    state
}