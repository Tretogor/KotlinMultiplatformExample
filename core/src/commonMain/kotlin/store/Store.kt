package com.jetbrains.handson.mpp.mobile.store

import com.jetbrains.handson.mpp.mobile.middleware.logginMiddleware
import com.jetbrains.handson.mpp.mobile.reducer.reducer
import com.jetbrains.handson.mpp.mobile.state.AppState
import org.reduxkotlin.Store
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.createThreadSafeStore

class Store {

    private lateinit var store: Store<AppState>

    fun initStore() {
        store = createThreadSafeStore(
            reducer,
            AppState(),
            applyMiddleware(
                logginMiddleware())
        )
    }

    fun subscribe(onChanged : (AppState) -> Unit) {
        store.subscribe { onChanged(store.state) }
    }

    fun dispatch(any: Any) {
        store.dispatch(any)
    }
}