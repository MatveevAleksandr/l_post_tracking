package com.example.l_post_tracking.model

data class OrderStorageFindDataModel(
    val trackNumber: String,
    val phoneNumber: String
)

fun OrderStorageFindDataModel.asAPIOrderStorageFindDataModel(): APIOrderStorageFindDataModel {
    return APIOrderStorageFindDataModel(
        trackNumber = this.trackNumber, phoneNumber = this.phoneNumber
    )
}