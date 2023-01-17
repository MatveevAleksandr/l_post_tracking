package com.example.l_post_tracking.storage

import android.util.Log
import com.example.l_post_tracking.model.FindOrderStorageModel
import com.example.l_post_tracking.model.ResultOrderStorageModel
import com.example.l_post_tracking.retrofit.OrderAPIMethods
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://l-post.ru/"

class APIOrderStorageImpl(): OrderStorage {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val orderAPI = retrofit.create<OrderAPIMethods>()

    override fun loadOrder(findOrderStorageModel: FindOrderStorageModel): ResultOrderStorageModel {
//        val res = orderAPI.loadOrder().execute()
        val lst: List<String>? = getHeaders()["set-cookie"]
        Log.e("AAA_AAA", lst!![0].toString())
//        Log.e("AAA_AAA", getHeaders().toString())
        return ResultOrderStorageModel(httpCode = 0)
    }

    private fun getHeaders(): Map<String, List<String>>{
        return orderAPI.getHeadersCall().execute().headers().toMultimap()
    }
}