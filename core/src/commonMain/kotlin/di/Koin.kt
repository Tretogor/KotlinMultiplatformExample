package com.jetbrains.handson.mpp.mobile.di

import com.jetbrains.handson.mpp.mobile.data.CustomApi
import com.jetbrains.handson.mpp.mobile.data.CustomRepository
import com.jetbrains.handson.mpp.mobile.sdk.ReduxSDK
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule)
}

// called by iOS etc
fun initKoin() = initKoin{}

val commonModule = module {
    single { CustomRepository() }
    single { CustomApi() }

    single { ReduxSDK.store }
}