package com.example.l_post_tracking.model

data class AppFindOrderResultDataModel(
    val customerNumber: String? = null,
    val orderNumber: String? = null,
    val orderType: Int? = null,
    val statusDescription: String? = null,
    val deliveryDatePlan: String? = null,
    val timeFrom: String? = null,
    val timeTo: String? = null,
    val isCourier: Boolean? = null
)