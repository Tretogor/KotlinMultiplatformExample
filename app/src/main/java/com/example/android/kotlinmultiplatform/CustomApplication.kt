package com.example.android.kotlinmultiplatform

import android.app.Application
import com.example.android.kotlinmultiplatform.di.appModule
import com.jetbrains.handson.mpp.mobile.di.commonModule
import com.jetbrains.handson.mpp.mobile.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            //androidLogger()
            androidContext(this@CustomApplication)
            modules(commonModule, appModule)
        }
    }
}