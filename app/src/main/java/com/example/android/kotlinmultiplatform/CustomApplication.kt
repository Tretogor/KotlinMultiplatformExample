package com.example.android.kotlinmultiplatform

import android.app.Application
import android.content.Intent
import android.net.Uri
import com.example.android.kotlinmultiplatform.di.appModule
import com.jetbrains.handson.mpp.mobile.di.initKoin
import com.jetbrains.handson.mpp.mobile.listeners.FrameworkListener
import com.jetbrains.handson.mpp.mobile.sdk.ReduxSDK
import com.jetbrains.handson.mpp.mobile.store.Store
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

lateinit var store: Store

class CustomApplication : Application() {

    private val frameworkListener = object : FrameworkListener() {
        override fun onOpenUrl(url: String) {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(url))
            browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(browserIntent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@CustomApplication)
            modules(appModule)
        }
        //val reduxSDK = ReduxSDK()
    }
}