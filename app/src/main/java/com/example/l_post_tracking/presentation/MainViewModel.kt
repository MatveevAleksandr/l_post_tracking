package com.example.l_post_tracking.presentation

import androidx.lifecycle.ViewModel
import ru.lpost.domain.CallCCUseCase
import ru.lpost.domain.NewFindUseCase

class MainViewModel(private val callCCUseCase: CallCCUseCase, private val newFindUseCase: NewFindUseCase): ViewModel() {

}