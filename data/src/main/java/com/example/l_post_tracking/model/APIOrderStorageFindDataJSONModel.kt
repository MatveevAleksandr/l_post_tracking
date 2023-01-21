package com.example.l_post_tracking.model

import com.google.gson.annotations.SerializedName

data class APIOrderStorageFindDataModel(
    @SerializedName("trackNumber") val trackNumber: String,
    @SerializedName("phoneNumber") val phoneNumber: String
)

data class APIOrderStorageFindDataJSONModel(@SerializedName("OrderTracking") val orderTracking: APIOrderStorageFindDataModel)
