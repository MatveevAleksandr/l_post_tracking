package com.example.l_post_tracking.model

import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject

data class APIOrderStorageFindResultModel(
//    @SerializedName("CustomerNumber") val customerNumber: String?,
//    @SerializedName("OrderNumber") val orderNumber: String?,
//    @SerializedName("Order_type") val orderType: String?,
//    @SerializedName("TXT_Status_RU") val statusDescription: String?,
//    @SerializedName("DeliveryDatePlan") val deliveryDatePlan: String?,

//    Any потому что ответ от АПИ тут может быть как JSONObject {}, так и JSONArray [] ¯\_(ツ)_/¯
    @SerializedName("data") val data: Any? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("error") val error: String? = null,
    val httpResponseCode: Int = 200
)