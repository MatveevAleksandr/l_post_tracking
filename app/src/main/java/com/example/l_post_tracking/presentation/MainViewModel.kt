package com.example.l_post_tracking.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.l_post_tracking.state.MainActivityFragmentState
import ru.lpost.domain.useCase.CallCCUseCase
import ru.lpost.domain.useCase.NewFindUseCase

class MainViewModel(private val callCCUseCase: CallCCUseCase, private val newFindUseCase: NewFindUseCase): ViewModel() {

    private var mainActivityFragmentState = MutableLiveData<MainActivityFragmentState>()

    fun getMainActivityFragmentState(): MutableLiveData<MainActivityFragmentState>{
        return mainActivityFragmentState
    }

    fun setMainActivityState(state: MainActivityFragmentState){
        mainActivityFragmentState.value = state
    }

}