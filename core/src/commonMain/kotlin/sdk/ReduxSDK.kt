package com.jetbrains.handson.mpp.mobile.sdk

import com.jetbrains.handson.mpp.mobile.listeners.FrameworkListener
import com.jetbrains.handson.mpp.mobile.store.Store

class ReduxSDK {

    private lateinit var store: Store

    fun initialize(frameworkListener: FrameworkListener) : Store{
        store = Store().apply {
            initStore(frameworkListener)
        }
        return store
    }
}