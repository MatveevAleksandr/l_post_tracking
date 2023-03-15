package com.example.l_post_tracking.storage

import com.example.l_post_tracking.model.*
import com.example.l_post_tracking.retrofit.APIOrderStorageRetrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException


private const val BASE_API_URL = "https://l-post.ru/"

class APIOrderStorageImpl : OrderStorage {
    private val orderRequestAPI =
        Retrofit.Builder().baseUrl(BASE_API_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create<APIOrderStorageRetrofit>()

    override fun loadOrderInfo(orderStorageFindDataModel: OrderStorageFindDataModel): OrderStorageFindResultModel {
        val apiFindData = orderStorageFindDataModel.asAPIOrderStorageFindDataModel()
        val requestBody = APIOrderStorageFindDataJSONModel(orderTracking = apiFindData)

        val orderStorageFindResultModel = try {
            val response = orderRequestAPI.getOrderInfo(body = requestBody).execute()
            response.asOrderStorageFindResultModel()
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
}