package com.wcisang.kotlinmultiplatform.reducer

import com.wcisang.kotlinmultiplatform.state.AppState
import com.wcisang.kotlinmultiplatform.state.ui.GetViewInformation
import com.wcisang.kotlinmultiplatform.state.ui.GetViewInformationState
import com.wcisang.kotlinmultiplatform.state.ui.InvalidInputInformation
import com.wcisang.kotlinmultiplatform.state.ui.InvalidInputState
import org.reduxkotlin.Reducer

val reducer: Reducer<AppState> = { state, action ->
    var state = state
    if (action is GetViewInformation) {
        state = GetViewInformationState(action.id)
    }else if (action is InvalidInputInformation){
       state = InvalidInputState(action.message, action.id)
    }else {
        state = AppState()
    }
    state
}