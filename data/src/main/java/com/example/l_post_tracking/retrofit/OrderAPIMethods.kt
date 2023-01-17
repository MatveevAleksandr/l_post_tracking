package com.example.l_post_tracking.retrofit

import com.example.l_post_tracking.model.ResultOrderStorageModel
import retrofit2.Call
import retrofit2.http.GET

interface OrderAPIMethods {
    @GET("tracking/check/")
    fun getHeadersCall(): Call<Map<String, String>>

    @GET("tracking/check/")
    fun getOrderinfo(): ResultOrderStorageModel
}