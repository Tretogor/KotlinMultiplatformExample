package com.wcisang.kotlinmultiplatform.listeners

abstract class FrameworkListener {

    abstract fun onOpenUrl(url: String)
    abstract fun onPhoneCall(phone: String)
}