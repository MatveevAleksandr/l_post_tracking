package com.example.l_post_tracking.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.l_post_tracking.model.*
import com.example.l_post_tracking.usecase.CallCCUseCase
import com.example.l_post_tracking.usecase.FindByOrderOrTrackNumUseCase
import com.example.l_post_tracking.usecase.FindByPhoneNumUseCase
import kotlinx.coroutines.*


class MainViewModel(
    private val callCCUseCase: CallCCUseCase,
    private val findByOrderOrTrackNumUseCase: FindByOrderOrTrackNumUseCase,
    private val findByPhoneNumUseCase: FindByPhoneNumUseCase
) : ViewModel() {

    private var mainActivityState = MutableLiveData<MainActivityState>()

    private var coroutineJob: Job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineJob)

    init {
        setMainActivityState(FindByNumOrTrackMainActivityState(errorMsg = null))
    }

    fun getMainActivityFragmentState(): MutableLiveData<MainActivityState> {
        return mainActivityState
    }

    private fun setMainActivityState(state: MainActivityState) {
        mainActivityState.value = state
    }

    fun callCCClick() {
        if (coroutineJob.isActive) {
            coroutineJob.cancel()
        }
        callCCUseCase.exec()
    }

    fun newSearchClick() {
        if (coroutineJob.isActive) {
            coroutineJob.cancel()
        }
        setMainActivityState(FindByNumOrTrackMainActivityState(errorMsg = null))
    }

    fun findByOrderOrTrackNumClick(orderOrTrackNum: String) {
        if (orderOrTrackNum.isEmpty()) {
            setMainActivityState(FindByNumOrTrackMainActivityState(errorMsg = "Введите номер или трек-номер отправления"))
            return
        }

        setMainActivityState(WaitingMainActivityState)

//       val appFindOrderDataModel = AppFindOrderDataModel(orderOrTrackNum = orderOrTrackNum, phoneNum = null)
         val appFindOrderDataModel = AppFindOrderDataModel(orderOrTrackNum = "2206/230P", phoneNum = null)

        coroutineJob = coroutineScope.launch(Dispatchers.IO) {

            val findByOrderOrTrackNumResult =
                findByOrderOrTrackNumUseCase.exec(appFindOrderDataModel)

            withContext(Dispatchers.Main) {
                val stateModel = when (findByOrderOrTrackNumResult) {
                    is DataLoaded -> {
                        ResultMainActivityState(orderData = findByOrderOrTrackNumResult.data)
                    }
                    is GetError -> {
                        FindByNumOrTrackMainActivityState(errorMsg = findByOrderOrTrackNumResult.errMessage)
                    }
                    is NeedAddPhoneNumberForSearch -> {
                        FindByPhoneMainActivityState(
                            orderNum = orderOrTrackNum, errorMsg = null
                        )
                    }
                }
                setMainActivityState(stateModel)
            }
        }
    }
}
