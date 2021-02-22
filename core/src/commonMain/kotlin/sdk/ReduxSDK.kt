package com.wcisang.kotlinmultiplatform.sdk

import com.wcisang.kotlinmultiplatform.listeners.FrameworkListener
import com.wcisang.kotlinmultiplatform.store.Store

class ReduxSDK {

    private lateinit var store: Store

    fun initialize(frameworkListener: FrameworkListener) : Store{
        store = Store().apply {
            initStore(frameworkListener)
        }
        return store
    }
}