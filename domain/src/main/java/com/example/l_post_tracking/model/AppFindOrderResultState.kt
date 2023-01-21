package com.example.l_post_tracking.model

sealed class AppFindOrderResultState
data class DataLoaded(val data: AppFindOrderResultDataModel) : AppFindOrderResultState()
object NeedAddPhoneNumberForSearch : AppFindOrderResultState()
data class GetError(val errMessage: String) : AppFindOrderResultState()