package com.jetbrains.handson.mpp.mobile.sdk

import com.jetbrains.handson.mpp.mobile.listeners.FrameworkListener
import com.jetbrains.handson.mpp.mobile.store.Store

class ReduxSDK {
    companion object {
        internal lateinit var store: Store

        fun initialize(frameworkListener: FrameworkListener) {
            store = Store().apply {
                initStore(frameworkListener)
            }
        }
    }
}