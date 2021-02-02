package com.jetbrains.handson.mpp.mobile.thunks

import com.jetbrains.handson.mpp.mobile.data.CustomRepository
import com.jetbrains.handson.mpp.mobile.state.AppState
import com.jetbrains.handson.mpp.mobile.state.ui.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.reduxkotlin.Thunk
import kotlin.coroutines.CoroutineContext

class NetworkThunks(networkContext: CoroutineContext, private val repository: CustomRepository) {

    private val networkScope = CoroutineScope(networkContext)

    fun makeCall(query: String) : Thunk<AppState> =  { dispatch, getState, extraArg ->
        dispatch(LoadingState())
        networkScope.launch {

        }
    }

}