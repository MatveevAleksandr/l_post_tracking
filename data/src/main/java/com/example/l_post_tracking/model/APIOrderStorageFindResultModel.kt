package com.example.l_post_tracking.model


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.Response


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

fun Response<APIOrderStorageFindResultModel>.asOrderStorageFindResultModel(): OrderStorageFindResultModel{
    /**
     * т.к. в data из ответа в виду кривого апи может прийти как объект {}, так и массив [], так и другая вложенность,
     * то data надо обрабатывать по условиям. Как только исправится формат ответа, то тип поля data просто меняется с Any на APIOrderStorageFindResultDataModel
     */
    val apiResponse = this
    val data = Gson().toJsonTree(apiResponse.body()?.data)
    val dataModel = if (data.isJsonObject) {
        val dataJsonObj = data.asJsonObject
//            всё из trackingData и pickupData надо вынести на уровень выше
        if (dataJsonObj.get("trackingData") != null) dataJsonObj.get("trackingData").asJsonObject.entrySet()
            .forEach {
                dataJsonObj.add(it.key, it.value)
            }
        if (dataJsonObj.get("pickupData") != null) dataJsonObj.get("pickupData").asJsonObject.entrySet()
            .forEach {
                dataJsonObj.add(it.key, it.value)
            }
        dataJsonObj.remove("trackingData")
        dataJsonObj.remove("pickupData")
        Gson().fromJson(
            dataJsonObj.get("trackingData") ?: dataJsonObj,
            APIOrderStorageFindResultDataModel::class.java
        )
    } else APIOrderStorageFindResultDataModel()


    return if (!apiResponse.isSuccessful) {
        OrderStorageFindResultModel(
            isDataLoaded = false,
            isNeedAddPhoneNum = false,
            errorMessage = apiResponse.errorBody().toString()
        )
    } else {
        when (apiResponse.body()!!.error) {
            "" -> {
                OrderStorageFindResultModel(
                    isDataLoaded = true,
                    isNeedAddPhoneNum = false,
                    customerNumber = dataModel?.customerNumber,
                    orderNumber = dataModel?.orderNumber,
                    orderType = dataModel?.orderType?.toInt(),
                    statusDescription = dataModel?.statusDescription,
                    deliveryDatePlan = dataModel?.deliveryDatePlan,
                    timeFrom = dataModel?.timeFrom,
                    timeTo = dataModel?.timeTo,
                    isCourier = dataModel?.isCourier == "1",
                    canPayCard = dataModel?.canPayCard == 1,
                    canPayCash = dataModel?.canPayCash == 1,
                    pvzAddress = dataModel?.pvzAddress,
                    pvzAddressDop = dataModel?.pvzAddressDop
                )

            }
            "2" -> {
                OrderStorageFindResultModel(isDataLoaded = false, isNeedAddPhoneNum = true)
            }
            "3" -> {
                OrderStorageFindResultModel(
                    isDataLoaded = false,
                    isNeedAddPhoneNum = false,
                    errorMessage = "Заказ не найден. Проверьте номер телефона и попробуйте снова, или свяжитесь с поддержкой 8 800 700-1006"
                )
            }
            else -> {
                OrderStorageFindResultModel(
                    isDataLoaded = false,
                    isNeedAddPhoneNum = false,
                    errorMessage = "Ошибка загрузки. Код 102. Обратитесь в поддержку"
                )
            }
        }
    }
}