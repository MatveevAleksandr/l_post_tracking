package com.example.l_post_tracking.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.l_post_tracking.model.AppFindOrderDataModel
import com.example.l_post_tracking.model.MainActivityFragment
import com.example.l_post_tracking.model.MainActivityFragmentStateModel
import com.example.l_post_tracking.usecase.CallCCUseCase
import com.example.l_post_tracking.usecase.FindByOrderOrTrackNumUseCase
import com.example.l_post_tracking.usecase.FindByPhoneNumUseCase
import kotlinx.coroutines.*

class MainViewModel(
    private val callCCUseCase: CallCCUseCase,
    private val findByOrderOrTrackNumUseCase: FindByOrderOrTrackNumUseCase,
    private val findByPhoneNumUseCase: FindByPhoneNumUseCase
) : ViewModel() {

    private var mainActivityFragmentStateModel = MutableLiveData<MainActivityFragmentStateModel>()

    private val coroutineJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineJob)

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

    fun findByOrderOrTrackNumClick(orderOrTrackNum: String) {
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

//        val appFindOrderDataModel = AppFindOrderDataModel(orderOrTrackNum = orderOrTrackNum, phoneNum = null)
        val appFindOrderDataModel =
            AppFindOrderDataModel(orderOrTrackNum = "2206/230P", phoneNum = null)

        coroutineScope.launch(Dispatchers.IO) {
            val findByOrderOrTrackNumResult =
                findByOrderOrTrackNumUseCase.exec(appFindOrderDataModel)


            withContext(Dispatchers.Main) {
                when (findByOrderOrTrackNumResult.errorFromJson) {
                    "3" -> {
                        setMainActivityState(
                            MainActivityFragmentStateModel(
                                mainActivityFragment = MainActivityFragment.FIND_BY_NUM_OR_TRACK,
                                errorMsg = "Заказ не найден. Уточните номер заказа и попробуйте снова, или свяжитесь с поддержкой\n" +
                                        "8 800 700-1006"
                            )
                        )
                    }
                    "2" -> {
                        setMainActivityState(
                            MainActivityFragmentStateModel(
                                mainActivityFragment = MainActivityFragment.FIND_BY_PHONE,
                                errorMsg = null
                            )
                        )
                    }
                    else -> {
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
    }
}