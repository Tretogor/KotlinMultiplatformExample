package com.example.android.kotlinmultiplatform.di

import com.example.android.kotlinmultiplatform.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get()) }
}