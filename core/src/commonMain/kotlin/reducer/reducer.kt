package com.wcisang.kotlinmultiplatform.reducer

import com.wcisang.kotlinmultiplatform.state.AppState
import com.wcisang.kotlinmultiplatform.state.ui.*
import org.reduxkotlin.Reducer

val reducer: Reducer<AppState> = { state, action ->
    var state = state
    if (action is GetViewInformation) {
        state = GetViewInformationState(action.id)
    }else if (action is InvalidInputInformation){
       state = InvalidInputState(action.message, action.id)
    }else if (action is LoadingAction){
        state = LoadingState()
    }else {
        state = AppState()
    }
    state
}