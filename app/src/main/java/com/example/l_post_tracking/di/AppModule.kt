package com.example.l_post_tracking.di

import android.content.Context
import com.example.l_post_tracking.usecase.CallCCUseCase
import com.example.l_post_tracking.usecase.FindAddressInMapUseCase
import com.example.l_post_tracking.usecase.FindByOrderOrTrackNumUseCase
import com.example.l_post_tracking.usecase.FindByPhoneNumUseCase
import com.example.l_post_tracking.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideCallCCUseCase(context: Context): CallCCUseCase {
        return CallCCUseCase(context = context)
    }

    @Provides
    fun provideFindAddressInMapUseCase(context: Context): FindAddressInMapUseCase {
        return FindAddressInMapUseCase(context = context)
    }

    @Provides
    fun provideMainViewModel(
        callCCUseCase: CallCCUseCase,
        findAddressInMapUseCase: FindAddressInMapUseCase,
        findByOrderOrTrackNumUseCase: FindByOrderOrTrackNumUseCase,
        findByPhoneNumUseCase: FindByPhoneNumUseCase
    ): MainViewModel {
        return MainViewModel(
            callCCUseCase = callCCUseCase,
            findAddressInMapUseCase = findAddressInMapUseCase,
            findByOrderOrTrackNumUseCase = findByOrderOrTrackNumUseCase,
            findByPhoneNumUseCase = findByPhoneNumUseCase
        )
    }
}





