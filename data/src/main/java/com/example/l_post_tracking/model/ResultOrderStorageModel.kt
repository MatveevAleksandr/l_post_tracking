package com.example.l_post_tracking.model

import org.json.JSONObject

data class ResultOrderStorageModel (
    val customerNumber: String?,
    val orderNumber: String?,
    val orderType: String?,
    val TXT_Status_RU: String?,
    val deliveryDatePlan: String?,


    val data: JSONObject?,
    val error: String?,
    val message: String?
)