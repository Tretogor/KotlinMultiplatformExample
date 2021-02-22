package com.wcisang.kotlinmultiplatform.di

import com.wcisang.kotlinmultiplatform.data.CustomApi
import com.wcisang.kotlinmultiplatform.data.CustomRepository
import com.wcisang.kotlinmultiplatform.sdk.ReduxSDK
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
}