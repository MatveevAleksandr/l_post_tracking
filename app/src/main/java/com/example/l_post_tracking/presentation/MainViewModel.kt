package com.example.l_post_tracking.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.l_post_tracking.state.MainActivityFragmentState
import com.example.l_post_tracking.usecase.CallCCUseCase
import com.example.l_post_tracking.usecase.NewFindUseCase

class MainViewModel(app: Application, private val callCCUseCase: CallCCUseCase, private val newFindUseCase: NewFindUseCase): AndroidViewModel(app) {

    private var mainActivityFragmentState = MutableLiveData<MainActivityFragmentState>()

    fun getMainActivityFragmentState(): MutableLiveData<MainActivityFragmentState>{
        return mainActivityFragmentState
    }

    fun setMainActivityState(state: MainActivityFragmentState){
        mainActivityFragmentState.value = state
    }

    fun callCC(){
        callCCUseCase.exec(app = getApplication())
    }
}