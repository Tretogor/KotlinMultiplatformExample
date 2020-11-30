package com.jetbrains.handson.mpp.mobile.sdk

import com.jetbrains.handson.mpp.mobile.middleware.ReduxNavigation
import com.jetbrains.handson.mpp.mobile.store.Store

class ReduxSDK {
    companion object {
        internal lateinit var store: Store

        fun initialize(reduxNavigation: ReduxNavigation) {
            store = Store().apply {
                initStore(reduxNavigation)
            }
        }
    }
}