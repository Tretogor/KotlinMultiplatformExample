package com.example.android.kotlinmultiplatform.di

import com.example.android.kotlinmultiplatform.CustomApplication
import com.example.android.kotlinmultiplatform.MainViewModel
import com.example.android.kotlinmultiplatform.views.button.PocButtonViewModel
import com.example.android.kotlinmultiplatform.views.input.PocInputViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get()) }

    viewModel { PocInputViewModel(get()) }
    viewModel { PocButtonViewModel(get()) }

   single { (androidContext() as CustomApplication).store }
}