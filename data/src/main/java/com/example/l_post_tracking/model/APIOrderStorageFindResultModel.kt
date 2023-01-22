package com.example.l_post_tracking.model

import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject


data class APIOrderStorageFindResultDataModel(
    @SerializedName("CustomerNumber") val customerNumber: String?,
    @SerializedName("OrderNumber") val orderNumber: String?,
    @SerializedName("Order_type") val orderType: String?,
    @SerializedName("TXT_Status_RU") val statusDescription: String?,
    @SerializedName("DeliveryDatePlan") val deliveryDatePlan: String?,
    @SerializedName("TimeFrom") val timeFrom: String? = null,
    @SerializedName("TimeTo") val timeTo: String? = null,
    @SerializedName("isCourier") val isCourier: Boolean? = null
)

data class APIOrderStorageFindResultModel(
    @SerializedName("data") val data: APIOrderStorageFindResultDataModel? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("error") val error: String? = null
)