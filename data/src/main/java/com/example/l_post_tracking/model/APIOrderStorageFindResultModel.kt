package com.example.l_post_tracking.model


import com.google.gson.annotations.SerializedName


data class APIOrderStorageFindResultDataModel(
    @SerializedName("CustomerNumber") val customerNumber: String? = null,
    @SerializedName("OrderNumber") val orderNumber: String? = null,
    @SerializedName("Order_type") val orderType: String? = null,
    @SerializedName("TXT_Status_RU") val statusDescription: String? = null,
    @SerializedName("DeliveryDatePlan") val deliveryDatePlan: String? = null,
    @SerializedName("address") val pvzAddress: String? = null,
    @SerializedName("location_description") val pvzAddressDop: String? = null,
    @SerializedName("TimeFrom") val timeFrom: String? = null,
    @SerializedName("TimeTo") val timeTo: String? = null,
    @SerializedName("isCourier") val isCourier: String? = null,
    @SerializedName("is_cash") val canPayCash: Int? = null,
    @SerializedName("is_card") val canPayCard: Int? = null
)

class APIOrderStorageFindResultModel(
    @SerializedName("data") var data: Any? = null,
    @SerializedName("error") val error: String? = null
)