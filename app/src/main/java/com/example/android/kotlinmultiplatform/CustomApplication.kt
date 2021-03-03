package com.example.android.kotlinmultiplatform

import android.app.Application
import android.content.Intent
import android.net.Uri
import com.example.android.kotlinmultiplatform.di.appModule
import com.wcisang.kotlinmultiplatform.di.initKoin
import com.wcisang.kotlinmultiplatform.listeners.FrameworkListener
import com.wcisang.kotlinmultiplatform.listeners.NavigationListener
import com.wcisang.kotlinmultiplatform.model.Screen
import com.wcisang.kotlinmultiplatform.sdk.ReduxSDK
import com.wcisang.kotlinmultiplatform.store.Store
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger


class CustomApplication : Application() {

    lateinit var store: Store

    private val frameworkListener = object : FrameworkListener() {
        override fun onOpenUrl(url: String) {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(url))
            browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(browserIntent)
        }

        override fun onPhoneCall(phone: String) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phone")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private val navigationListener = object : NavigationListener {
        override fun goTo(screen: Screen) {
            startActivity(AuxActivity.newInstance(this@CustomApplication, screen))
        }

    }

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@CustomApplication)
            modules(appModule)
        }

        store = ReduxSDK().initialize(frameworkListener, navigationListener)
    }
}