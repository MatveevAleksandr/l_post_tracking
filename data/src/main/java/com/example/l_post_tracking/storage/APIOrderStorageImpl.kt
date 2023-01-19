package com.example.l_post_tracking.storage

import android.util.Log
import com.example.l_post_tracking.model.FindOrderStorageModel
import com.example.l_post_tracking.model.ResultOrderStorageModel
import com.example.l_post_tracking.retrofit.APIOrderStorageRetrofit
import com.google.gson.GsonBuilder
import okhttp3.Headers
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


    override fun loadOrderInfo(findOrderStorageModel: FindOrderStorageModel): ResultOrderStorageModel {


//        val body = FindOrderStorageModel(trackNumber = "64637", phoneNumber = "")

//            "{\"OrderTracking\":{\"trackNumber\":\"64637\",\"phoneNumber\":\"\"}}"

        val body = JSONObject("{\"OrderTracking\":{\"trackNumber\":\"64637\",\"phoneNumber\":\"\"}}")

        Log.e("AAA_AAA", body.toString())

        Log.e("AAA_AAA", orderRequestAPI.getOrderInfo(body).execute().body().toString())


        return ResultOrderStorageModel(null, null, null, null, null, null, null, null)
    }
}