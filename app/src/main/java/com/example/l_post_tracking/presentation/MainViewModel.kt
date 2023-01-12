package com.example.l_post_tracking.presentation


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.l_post_tracking.state.MainActivityFragmentState
import com.example.l_post_tracking.usecase.CallCCUseCase

class MainViewModel(private val callCCUseCase: CallCCUseCase): ViewModel() {

    private var mainActivityFragmentState = MutableLiveData<MainActivityFragmentState>()

    init {
        Log.e("AAA_AAA", "init VM")
        setMainActivityState(MainActivityFragmentState.RESULT)
    }

    fun getMainActivityFragmentState(): MutableLiveData<MainActivityFragmentState>{
        return mainActivityFragmentState
    }

    fun setMainActivityState(state: MainActivityFragmentState){
        mainActivityFragmentState.value = state
    }

    fun callCC(){
        callCCUseCase.exec()
    }
}