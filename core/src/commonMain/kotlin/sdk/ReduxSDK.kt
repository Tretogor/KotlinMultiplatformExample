package com.jetbrains.handson.mpp.mobile.sdk

import com.jetbrains.handson.mpp.mobile.listeners.FrameworkListener
import com.jetbrains.handson.mpp.mobile.store.Store
import kotlin.jvm.JvmStatic

class ReduxSDK {
    companion object {
        @JvmStatic
        lateinit var store: Store

        @JvmStatic
        fun initialize(frameworkListener: FrameworkListener) {
            store = Store().apply {
                initStore(frameworkListener)
            }
        }
    }
}