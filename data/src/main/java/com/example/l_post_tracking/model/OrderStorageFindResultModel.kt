package com.example.l_post_tracking.model

data class OrderStorageFindResultModel(
    val isDataLoaded: Boolean = false,
    val errorMessage: String? = null,
    val isNeedAddPhoneNum: Boolean = false,
    val customerNumber: String? = null,
    val orderNumber: String? = null,
    val orderType: Int? = null,
    val statusDescription: String? = null,
    val deliveryDatePlan: String? = null,
    val pvzAddress: String? = null,
    val pvzAddressDop: String? = null,
    val timeFrom: String? = null,
    val timeTo: String? = null,
    val isCourier: Boolean? = null,
    val canPayCard: Boolean? = null,
    val canPayCash: Boolean? = null
)