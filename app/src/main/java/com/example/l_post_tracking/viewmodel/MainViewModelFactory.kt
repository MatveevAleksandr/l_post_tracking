package com.example.l_post_tracking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.l_post_tracking.usecase.CallCCUseCase
import com.example.l_post_tracking.usecase.FindByOrderOrTrackNumUseCase
import com.example.l_post_tracking.usecase.FindByPhoneNumUseCase

class MainViewModelFactory(private val callCCUseCase: CallCCUseCase,
                           private val findByOrderOrTrackNumUseCase: FindByOrderOrTrackNumUseCase,
                           private val findByPhoneNumUseCase: FindByPhoneNumUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(callCCUseCase, findByOrderOrTrackNumUseCase, findByPhoneNumUseCase) as T
    }
}