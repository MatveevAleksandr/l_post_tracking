package com.example.l_post_tracking.repository

import com.example.l_post_tracking.model.AppFindOrderDataModel
import com.example.l_post_tracking.model.AppFindOrderResultState
import com.example.l_post_tracking.model.OrderStorageFindDataModel
import com.example.l_post_tracking.model.asAppFindResultModel
import com.example.l_post_tracking.storage.OrderStorage


private fun AppFindOrderDataModel.asOrderStorageFindDataModel(): OrderStorageFindDataModel {
    return OrderStorageFindDataModel(
        trackNumber = this.orderOrTrackNum, phoneNumber = this.phoneNum ?: ""
    )
}

class OrderRepositoryImpl(private val storage: OrderStorage) : OrderRepository {
    override fun loadOrder(appFindOrderDataModel: AppFindOrderDataModel): AppFindOrderResultState {
        val findOrderStorageModel = appFindOrderDataModel.asOrderStorageFindDataModel()
        val resultOrderStorageModel = storage.loadOrderInfo(findOrderStorageModel)
        return resultOrderStorageModel.asAppFindResultModel()
    }
}