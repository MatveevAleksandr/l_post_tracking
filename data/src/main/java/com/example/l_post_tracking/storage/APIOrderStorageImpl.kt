package com.example.l_post_tracking.storage

import android.util.JsonReader
import android.util.Log
import com.example.l_post_tracking.model.*
import com.example.l_post_tracking.retrofit.APIOrderStorageRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException


private const val BASE_API_URL = "https://l-post.ru/"

class APIOrderStorageImpl() : OrderStorage {

    private val orderRequestAPI =
        Retrofit.Builder().baseUrl(BASE_API_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create<APIOrderStorageRetrofit>()

    override fun loadOrderInfo(orderStorageFindDataModel: OrderStorageFindDataModel): OrderStorageFindResultModel {
        val apiFindData =
            convertOrderStorageFindDataModelToAPIOrderStorageFindDataModel(orderStorageFindDataModel)
        val requestBody = APIOrderStorageFindDataJSONModel(orderTracking = apiFindData)

        val orderStorageFindResultModel = try {
            val response = orderRequestAPI.getOrderInfo(requestBody).execute()
            convertAPIResponseToOrderStorageFindResultModel(response)
        } catch (e: IOException) {
            OrderStorageFindResultModel(
                isDataLoaded = false,
                isNeedAddPhoneNum = false,
                errorMessage = "Ошибка загрузки. Код 100. Обратитесь в поддержку"
            )
        } catch (e: RuntimeException) {
            OrderStorageFindResultModel(
                isDataLoaded = false,
                isNeedAddPhoneNum = false,
                errorMessage = "Ошибка загрузки. Код 101. Обратитесь в поддержку"
            )
        }
        return orderStorageFindResultModel
    }

    private fun convertOrderStorageFindDataModelToAPIOrderStorageFindDataModel(fdm: OrderStorageFindDataModel): APIOrderStorageFindDataModel {
        return APIOrderStorageFindDataModel(
            trackNumber = fdm.trackNumber, phoneNumber = fdm.phoneNumber
        )
    }

    private fun convertAPIResponseToOrderStorageFindResultModel(apiResponse: Response<APIOrderStorageFindResultModel>): OrderStorageFindResultModel {
        /**
         * т.к. в data из ответа в виду кривого апи может прийти как объект {}, так и массив [], так и другая вложенность,
         * то data надо обрабатывать по условиям. Как только исправится формат ответа, то тип поля data просто меняется с Any на APIOrderStorageFindResultDataModel
         */
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
                        errorMessage = "Системная ошибка. Пожалуйста свяжитесь с поддержкой 8 800 700-1006"
                    )
                }
            }
        }
    }
}