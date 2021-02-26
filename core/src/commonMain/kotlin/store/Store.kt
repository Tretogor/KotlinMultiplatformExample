package com.wcisang.kotlinmultiplatform.store

import com.wcisang.kotlinmultiplatform.listeners.FrameworkListener
import com.wcisang.kotlinmultiplatform.listeners.NavigationListener
import com.wcisang.kotlinmultiplatform.middleware.frameworkMiddleware
import com.wcisang.kotlinmultiplatform.middleware.logginMiddleware
import com.wcisang.kotlinmultiplatform.middleware.navigationMiddleware
import com.wcisang.kotlinmultiplatform.reducer.reducer
import com.wcisang.kotlinmultiplatform.state.AppState
import org.reduxkotlin.Store
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.createThreadSafeStore

class Store {

    private lateinit var store: Store<AppState>

    fun initStore(frameworkListener: FrameworkListener, navigationListener: NavigationListener) {
        store = createThreadSafeStore(
            reducer,
            AppState(),
            applyMiddleware(
                logginMiddleware(),
                frameworkMiddleware(frameworkListener),
                navigationMiddleware(navigationListener)
            )
        )
    }

    fun subscribe(onChanged : (AppState) -> Unit) {
        store.subscribe { onChanged(store.state) }
    }

    fun dispatch(any: Any) {
        store.dispatch(any)
    }
}