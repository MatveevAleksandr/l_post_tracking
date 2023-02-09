package com.example.l_post_tracking.model

/**
 * Класс, который описывает визуальное состояние главного экрана. Какой фрагмент с какимим данными
 * видит пользователь
 */

sealed class MainScreenState
data class FindByNumOrTrackMainScreenState(val errorMsg: String?) : MainScreenState()
data class FindByPhoneMainScreenState(val orderNum: String, val errorMsg: String?) : MainScreenState()
object WaitingMainScreenState: MainScreenState()
data class ResultMainScreenState(val orderData: AppFindOrderResultDataModel) : MainScreenState()