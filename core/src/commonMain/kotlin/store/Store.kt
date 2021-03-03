package com.wcisang.kotlinmultiplatform.store

import com.wcisang.kotlinmultiplatform.data.CustomApi
import com.wcisang.kotlinmultiplatform.listeners.FrameworkListener
import com.wcisang.kotlinmultiplatform.listeners.NavigationListener
import com.wcisang.kotlinmultiplatform.middleware.*
import com.wcisang.kotlinmultiplatform.middleware.apiMiddleware
import com.wcisang.kotlinmultiplatform.middleware.frameworkMiddleware
import com.wcisang.kotlinmultiplatform.middleware.informationCollectorMiddleware
import com.wcisang.kotlinmultiplatform.middleware.logginMiddleware
import com.wcisang.kotlinmultiplatform.reducer.reducer
import com.wcisang.kotlinmultiplatform.state.AppState
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.reduxkotlin.Store
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.createThreadSafeStore

class Store : KoinComponent {

    private lateinit var store: Store<AppState>
    private val api : CustomApi by inject()

    fun initStore(frameworkListener: FrameworkListener, navigationListener: NavigationListener) {
        store = createThreadSafeStore(
            reducer,
            AppState(),
            applyMiddleware(
                logginMiddleware(),
                informationCollectorMiddleware(),
                frameworkMiddleware(frameworkListener),
                navigationMiddleware(navigationListener),
                apiMiddleware(api, navigationListener)
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