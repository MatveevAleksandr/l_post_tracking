package com.example.l_post_tracking.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.l_post_tracking.model.FindDataModel
import com.example.l_post_tracking.model.MainActivityFragment
import com.example.l_post_tracking.model.MainActivityFragmentStateModel
import com.example.l_post_tracking.usecase.CallCCUseCase
import com.example.l_post_tracking.usecase.FindByOrderOrTrackNumUseCase
import com.example.l_post_tracking.usecase.FindByPhoneNumUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val callCCUseCase: CallCCUseCase,
    private val findByOrderOrTrackNumUseCase: FindByOrderOrTrackNumUseCase,
    private val findByPhoneNumUseCase: FindByPhoneNumUseCase
) : ViewModel() {

    private var mainActivityFragmentStateModel = MutableLiveData<MainActivityFragmentStateModel>()

    init {
        setMainActivityState(
            MainActivityFragmentStateModel(
                mainActivityFragment = MainActivityFragment.FIND_BY_NUM_OR_TRACK,
                errorMsg = null
            )
        )
    }

    fun getMainActivityFragmentState(): MutableLiveData<MainActivityFragmentStateModel> {
        return mainActivityFragmentStateModel
    }

    private fun setMainActivityState(state: MainActivityFragmentStateModel) {
        mainActivityFragmentStateModel.value = state
    }

    fun callCCClick() {
        callCCUseCase.exec()
    }

    fun newSearchClick() {
        setMainActivityState(
            MainActivityFragmentStateModel(
                mainActivityFragment = MainActivityFragment.FIND_BY_NUM_OR_TRACK,
                errorMsg = null
            )
        )
    }

    suspend fun findByOrderOrTrackNumClick(orderOrTrackNum: String) {
        if (orderOrTrackNum.isEmpty()) {
            setMainActivityState(
                MainActivityFragmentStateModel(
                    mainActivityFragment = MainActivityFragment.FIND_BY_NUM_OR_TRACK,
                    errorMsg = "Введите номер или трек-номер отправления"
                )
            )
            return
        }
        setMainActivityState(
            MainActivityFragmentStateModel(
                mainActivityFragment = MainActivityFragment.WAITING,
                errorMsg = null
            )
        )
        val findDataModel = FindDataModel(orderOrTrackNum = orderOrTrackNum, phoneNum = null)
        coroutineScope {
            launch {
                findByOrderOrTrackNumUseCase.exec(findDataModel)
                delay(200)
                setMainActivityState(
                    MainActivityFragmentStateModel(
                        mainActivityFragment = MainActivityFragment.RESULT,
                        errorMsg = null
                    )
                )
            }
        }
    }
}