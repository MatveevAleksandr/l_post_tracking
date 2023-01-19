package com.example.l_post_tracking.retrofit

import okhttp3.Request
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIOrderStorageRetrofit {
    @GET("tracking/check")
    fun getHeadersCall(): Call<Request>

    @POST("tracking/check")
    fun getOrderInfoCall(
        @Header("content-type") contentType: String,
//        @Header("x-csrf-token") csrfToken: String,
        @Header("Cookie") cookie: String,
        @Body bodyJson: String
    ): Call<JSONObject>

    @Headers("content-type: application/json",
    "cookie: _csrf-frontend=2aa23aea29fd8e77764fa893913b4f74958bd9354bfdd308dbc7f0d7fc9510b4a%3A2%3A%7Bi%3A0%3Bs%3A14%3A%22_csrf-frontend%22%3Bi%3A1%3Bs%3A32%3A%221aJ0Umw7QyJpWg5clOX6ENvg0EUgsyYk%22%3B%7D;",
    "x-csrf-token: OWP24Zj5S2Moa7T3JBXcvQIdgA0oySl4NA4lyxVRNTAIArzRzZQ8VHkS_odzcuneblLYO22HXx8ES3CsZihsWw==")

    @POST("tracking/check")
    fun test(): Call<JSONObject>
}

