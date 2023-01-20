package com.example.l_post_tracking.storage

import android.util.Log
import com.example.l_post_tracking.model.*
import com.example.l_post_tracking.retrofit.APIOrderStorageRetrofit
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


private const val BASE_API_URL = "https://l-post.ru/"

class APIOrderStorageImpl() : OrderStorage {

    private val orderRequestAPI = Retrofit.Builder()
        .baseUrl(BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<APIOrderStorageRetrofit>()

    override fun loadOrderInfo(orderStorageFindDataModel: OrderStorageFindDataModel): OrderStorageFindResultModel {
        val apiFindData =
            convertOrderStorageFindDataModelToAPIOrderStorageFindDataModel(orderStorageFindDataModel)
        val requestBody = APIOrderStorageFindDataJSONModel(orderTracking = apiFindData)

        val response = orderRequestAPI.getOrderInfo(requestBody).execute()

        val apiOrderStorageFindResultModel =
            if (response.isSuccessful) {
                response.body()!!
            } else {
                APIOrderStorageFindResultModel(httpResponseCode = response.code())
            }

        Log.e("AAA_AAA", apiOrderStorageFindResultModel.toString())

        return convertAPIOrderStorageFindResultModelToOrderStorageFindResultModel(apiOrderStorageFindResultModel)
    }

    private fun convertOrderStorageFindDataModelToAPIOrderStorageFindDataModel(fdm: OrderStorageFindDataModel): APIOrderStorageFindDataModel {
        return APIOrderStorageFindDataModel(
            trackNumber = fdm.trackNumber,
            phoneNumber = fdm.phoneNumber
        )
    }

    private fun convertAPIOrderStorageFindResultModelToOrderStorageFindResultModel(frm: APIOrderStorageFindResultModel): OrderStorageFindResultModel {
        return OrderStorageFindResultModel(customerNumber = frm.message, errorFromJson = frm.error)
    }
}