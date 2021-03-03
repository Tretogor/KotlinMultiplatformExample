package com.wcisang.kotlinmultiplatform.middleware

import com.wcisang.kotlinmultiplatform.data.CustomApi
import com.wcisang.kotlinmultiplatform.listeners.NavigationListener
import com.wcisang.kotlinmultiplatform.model.actionrow.ApiAction
import com.wcisang.kotlinmultiplatform.model.actionrow.ApiGetActionRow
import com.wcisang.kotlinmultiplatform.state.AppState
import com.wcisang.kotlinmultiplatform.state.ui.LoadingAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.reduxkotlin.Store
import org.reduxkotlin.middleware

internal fun apiMiddleware(api: CustomApi, navigationListener: NavigationListener) = middleware<AppState> { store, next, action ->
    if (action is ApiAction) {
        if (action is ApiGetActionRow) {
            handleApiGet(store, action, api, navigationListener)
        }
    }

    val result = next(action)
    result
}

fun handleApiGet(store: Store<AppState>, action: ApiAction, api: CustomApi, navigationListener: NavigationListener) {
    store.dispatch(LoadingAction())
    GlobalScope.launch {
        val result = withContext(Dispatchers.Default) {
            api.makeGetCall()
        }
        navigationListener.goTo(result)
    }
}
