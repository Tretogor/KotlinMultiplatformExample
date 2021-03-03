package com.wcisang.kotlinmultiplatform.listeners

import com.wcisang.kotlinmultiplatform.model.Screen

interface NavigationListener {
    fun goTo(screen: Screen)
}