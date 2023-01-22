package com.example.l_post_tracking.storage

import android.util.Log
import com.example.l_post_tracking.model.*
import com.example.l_post_tracking.retrofit.APIOrderStorageRetrofit
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
                isDataLoaded = false, isNeedAddPhoneNum = false, errorMessage = e.message
            )
        } catch (e: RuntimeException) {
            OrderStorageFindResultModel(
                isDataLoaded = false, isNeedAddPhoneNum = false, errorMessage = e.message
            )
        }
        Log.e("AAA_AAA", orderStorageFindResultModel.toString())
        return orderStorageFindResultModel
    }

    private fun convertOrderStorageFindDataModelToAPIOrderStorageFindDataModel(fdm: OrderStorageFindDataModel): APIOrderStorageFindDataModel {
        return APIOrderStorageFindDataModel(
            trackNumber = fdm.trackNumber, phoneNumber = fdm.phoneNumber
        )
    }

    private fun convertAPIResponseToOrderStorageFindResultModel(apiResponse: Response<APIOrderStorageFindResultModel>): OrderStorageFindResultModel {
        return if (!apiResponse.isSuccessful) {
            OrderStorageFindResultModel(
                isDataLoaded = false,
                isNeedAddPhoneNum = false,
                errorMessage = apiResponse.errorBody().toString()
            )
        } else {
            when (apiResponse.body()!!.error) {
                "" -> {
                    val bodyData = apiResponse.body()!!.data
                    OrderStorageFindResultModel(
                        isDataLoaded = true,
                        isNeedAddPhoneNum = false,
                        customerNumber = bodyData?.customerNumber,
                        orderNumber = bodyData?.orderNumber,
                        orderType = bodyData?.orderType?.toInt(),
                        statusDescription = bodyData?.statusDescription,
                        deliveryDatePlan = bodyData?.deliveryDatePlan,
                        timeFrom = bodyData?.timeFrom,
                        timeTo = bodyData?.timeTo,
                        isCourier = bodyData?.isCourier
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