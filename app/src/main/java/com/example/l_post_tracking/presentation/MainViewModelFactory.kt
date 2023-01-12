package com.example.l_post_tracking.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.l_post_tracking.usecase.CallCCUseCase

class MainViewModelFactory(private val callCCUseCase: CallCCUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(callCCUseCase) as T
    }
}