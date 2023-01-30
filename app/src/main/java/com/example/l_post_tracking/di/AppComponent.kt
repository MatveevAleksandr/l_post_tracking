package com.example.l_post_tracking.di

import android.content.Context
import com.example.l_post_tracking.repository.OrderRepository
import com.example.l_post_tracking.storage.OrderStorage
import com.example.l_post_tracking.usecase.CallCCUseCase
import com.example.l_post_tracking.usecase.FindAddressInMapUseCase
import com.example.l_post_tracking.usecase.FindByOrderOrTrackNumUseCase
import com.example.l_post_tracking.usecase.FindByPhoneNumUseCase
import com.example.l_post_tracking.viewmodel.MainViewModel
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {
//    fun getOrderStorage(): OrderStorage
//    fun getOrderRepository(): OrderRepository
//    fun getFindByOrderOrTrackNumUseCase(): FindByOrderOrTrackNumUseCase
//    fun getFindByPhoneNumUseCase(): FindByPhoneNumUseCase
//    fun getCallCCUseCase(): CallCCUseCase
//    fun getFindAddressInMapUseCase(): FindAddressInMapUseCase
    fun getMainViewModel(): MainViewModel

    @Component.Factory
    interface AppComponentFactory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}