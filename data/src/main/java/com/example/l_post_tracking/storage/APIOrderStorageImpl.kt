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
private const val TOKEN_HEADER = "_csrf-frontend"

class APIOrderStorageImpl() : OrderStorage {

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val orderRequestAPI = Retrofit.Builder()
        .baseUrl("https://l-post.ru/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create<APIOrderStorageRetrofit>()

    override fun loadOrderInfo(findOrderStorageModel: FindOrderStorageModel): ResultOrderStorageModel {
//        val cookies = getHeaders()["set-cookie"]
//        Log.e("AAA_AAA", getHeaders().toString())
//        val csrfToken = cookies?.let { getCsrfTokenFromCookie(cookies = it) } ?: ""

       /* val result = orderRequestAPI.getOrderInfoCall(
            contentType = "application/json"
//            csrfToken = "c2afcac4331a4b55a917f2ae76b2f8f984bf6db637793532a6afa97dd57159ada%3A2%3A%7Bi%3A0%3Bs%3A14%3A%22_csrf-frontend%22%3Bi%3A1%3Bs%3A32%3A%22gm8x2W8sN0wSlbFsMfQUB0bXF0SOpC1I%22%3B%7D"
            ,orderRequestAPI.getHeadersCall().execute().headers()["set-cookie"].toString()
            , bodyJson = "{\"OrderTracking\":{\"trackNumber\":\"25065\",\"phoneNumber\":\"\"}}"
        ).execute()

        Log.e("AAA_AAA", result.headers().toString())*/


        val js = JSONObject("{\"OrderTracking\":{\"trackNumber\":\"2206/230P\",\"phoneNumber\":\"\"}}")
        val requestBody: MutableMap<String, String> = HashMap()
        requestBody["trackNumber"] = "2206/230P"

        getHeaders()

        Log.e("AAA_AAA", orderRequestAPI.test().execute().toString())
        Log.e("AAA_AAA", orderRequestAPI.test().execute().body().toString())



        return ResultOrderStorageModel(httpCode = 0)
    }

    private fun getHeaders(): Headers {
        Log.e("AAA_AAA", orderRequestAPI.getHeadersCall().execute().headers()["set-cookie"].toString())
        return orderRequestAPI.getHeadersCall().execute().headers()
    }

    private fun getCsrfTokenFromCookie(cookies: String): String {
        cookies.split(';').forEach {
//            Log.e("AAA_AAA", it)
        }
        return ""
    }
}