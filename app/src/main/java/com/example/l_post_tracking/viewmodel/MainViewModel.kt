package com.example.l_post_tracking.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.l_post_tracking.model.*
import com.example.l_post_tracking.usecase.CallCCUseCase
import com.example.l_post_tracking.usecase.FindAddressInMapUseCase
import com.example.l_post_tracking.usecase.FindByOrderOrTrackNumUseCase
import com.example.l_post_tracking.usecase.FindByPhoneNumUseCase
import kotlinx.coroutines.*


class MainViewModel(
    private val callCCUseCase: CallCCUseCase,
    private val findAddressInMapUseCase: FindAddressInMapUseCase,
    private val findByOrderOrTrackNumUseCase: FindByOrderOrTrackNumUseCase,
    private val findByPhoneNumUseCase: FindByPhoneNumUseCase
) : ViewModel() {

    private var mainActivityState = MutableLiveData<MainActivityState>()

    private var coroutineJob: Job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineJob)

    init {
        setMainActivityState(FindByNumOrTrackMainActivityState(errorMsg = null))
    }

    fun getMainActivityLiveDataState(): MutableLiveData<MainActivityState> {
        return mainActivityState
    }

    private fun setMainActivityState(state: MainActivityState) {
        mainActivityState.value = state
    }

    fun callCCClick() {
        if (!coroutineJob.isCancelled) {
            coroutineJob.cancel()
        }
        callCCUseCase.exec()
    }

    fun addressClick(address: String) {
        findAddressInMapUseCase.exec(address = address)
    }

    fun newSearchClick() {
        if (!coroutineJob.isCancelled) {
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
        val appFindOrderDataModel =
            AppFindOrderDataModel(orderOrTrackNum = orderOrTrackNum, phoneNum = null)
        //  val appFindOrderDataModel = AppFindOrderDataModel(orderOrTrackNum = "2206/230P", phoneNum = null)  // курьерка
//                      val appFindOrderDataModel = AppFindOrderDataModel(orderOrTrackNum = "123-0120-7815", phoneNum = null)       // самовывоз
        //                                  val appFindOrderDataModel = AppFindOrderDataModel(orderOrTrackNum = "22", phoneNum = null)       // уточнение тел
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

    fun findByPhoneNumClick(orderOrTrackNum: String, _phoneNum: String){
        if (_phoneNum.isEmpty()) {
            setMainActivityState(FindByPhoneMainActivityState(orderNum = orderOrTrackNum, errorMsg = "Введите номер телефона"))
            return
        }
        val phoneNum = reformatPhoneNumber(_phoneNumber = _phoneNum)
        if (orderOrTrackNum.isEmpty()) {
            setMainActivityState(FindByNumOrTrackMainActivityState(errorMsg = "Введите номер или трек-номер отправления"))
            return
        }
        setMainActivityState(WaitingMainActivityState)
        val appFindOrderDataModel =
            AppFindOrderDataModel(orderOrTrackNum = orderOrTrackNum, phoneNum = phoneNum)

        coroutineJob = coroutineScope.launch(Dispatchers.IO) {
            val findByPhoneNumResult =
                findByPhoneNumUseCase.exec(appFindOrderDataModel)
            withContext(Dispatchers.Main) {
                val stateModel = when (findByPhoneNumResult) {
                    is DataLoaded -> {
                        ResultMainActivityState(orderData = findByPhoneNumResult.data)
                    }
                    is GetError -> {
                        FindByPhoneMainActivityState(orderNum = orderOrTrackNum, errorMsg = findByPhoneNumResult.errMessage)
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

    /**
     * Привести номер телефона к читаемому формату для запросов
     */
    private fun reformatPhoneNumber(_phoneNumber: String): String{
        var phoneNumber: String = _phoneNumber.replace(" ","", true).replace("-","", true)
        phoneNumber = when {
            (phoneNumber.length < 11 ) -> "+7$phoneNumber"
            (phoneNumber.length == 11 ) -> "+7${phoneNumber.substring(1)}"
            else -> phoneNumber
        }
        return phoneNumber
    }
}
