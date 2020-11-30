package com.example.android.kotlinmultiplatform

import android.app.Application
import com.example.android.kotlinmultiplatform.di.appModule
import com.jetbrains.handson.mpp.mobile.di.commonModule
import com.jetbrains.handson.mpp.mobile.di.initKoin
import org.koin.android.ext.koin.androidContext


class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@CustomApplication)
            modules(commonModule, appModule)
        }
    }
}