package com.example.l_post_tracking.di

import com.example.l_post_tracking.usecase.CallCCUseCase
import com.example.l_post_tracking.usecase.FindAddressInMapUseCase
import com.example.l_post_tracking.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { CallCCUseCase(context = get()) }
    factory { FindAddressInMapUseCase(context = get()) }
    viewModel {
        MainViewModel(
            callCCUseCase = get(),
            findAddressInMapUseCase = get(),
            findByOrderOrTrackNumUseCase = get(),
            findByPhoneNumUseCase = get()
        )
    }
}