package com.example.l_post_tracking.di

import com.example.l_post_tracking.usecase.FindByOrderOrTrackNumUseCase
import com.example.l_post_tracking.usecase.FindByPhoneNumUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FindByOrderOrTrackNumUseCase(orderRepository = get()) }
    factory { FindByPhoneNumUseCase(orderRepository = get()) }
}