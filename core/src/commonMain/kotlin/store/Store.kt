package com.wcisang.kotlinmultiplatform.store

import com.wcisang.kotlinmultiplatform.listeners.FrameworkListener
import com.wcisang.kotlinmultiplatform.middleware.frameworkMiddleware
import com.wcisang.kotlinmultiplatform.middleware.logginMiddleware
import com.wcisang.kotlinmultiplatform.reducer.reducer
import com.wcisang.kotlinmultiplatform.state.AppState
import org.reduxkotlin.Store
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.createSameThreadEnforcedStore
import org.reduxkotlin.createThreadSafeStore

class Store {

    private lateinit var store: Store<AppState>

    fun initStore(frameworkListener: FrameworkListener) {
        store = createSameThreadEnforcedStore(
            reducer,
            AppState(),
            applyMiddleware(
                logginMiddleware(),
                frameworkMiddleware(frameworkListener)
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