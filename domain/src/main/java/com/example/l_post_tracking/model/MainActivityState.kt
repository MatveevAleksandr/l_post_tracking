package com.example.l_post_tracking.model

/**
 * Класс, который описывает визуальное состояние главного экрана. Какой фрагмент с какимим данными
 * видит пользователь
 */

sealed class MainActivityState
data class FindByNumOrTrackMainActivityState(val errorMsg: String?) : MainActivityState()
data class FindByPhoneMainActivityState(val orderNum: String, val errorMsg: String?) : MainActivityState()
object WaitingMainActivityState: MainActivityState()
data class ResultMainActivityState(val orderData: AppFindOrderResultDataModel) : MainActivityState()