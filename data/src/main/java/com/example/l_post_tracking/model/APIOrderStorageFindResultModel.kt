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
//    "CustomerNumber": "2206/230P",
//"OrderNumber": "ABC85975647",
//"Order_type": "1",
//"TXT_Status_RU": "Выполнено",
//"DeliveryDatePlan": "2022-08-15",
//"TimeFrom": "9",
//"TimeTo": "21",
//"AdressDelivery": "г. Москва",
//"DateSave": "2022-06-28 08:21:00",
//"ID_PickupPoint": "3812",
//"isCourier": "1"

//    Any потому что ответ от АПИ тут может быть как JSONObject {}, так и JSONArray [] ¯\_(ツ)_/¯
    @SerializedName("data") val data: Any? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("error") val error: String? = null,
    val httpResponseCode: Int = 200
)