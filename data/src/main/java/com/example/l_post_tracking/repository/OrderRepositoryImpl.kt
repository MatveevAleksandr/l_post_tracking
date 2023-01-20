package com.example.l_post_tracking.repository

import com.example.l_post_tracking.model.OrderStorageFindDataModel
import com.example.l_post_tracking.model.OrderStorageFindResultModel
import com.example.l_post_tracking.storage.OrderStorage
import com.example.l_post_tracking.model.AppFindOrderDataModel
import com.example.l_post_tracking.model.AppFindOrderResultModel

class OrderRepositoryImpl(private val storage: OrderStorage): OrderRepository {
    override fun loadOrder(appFindOrderDataModel: AppFindOrderDataModel): AppFindOrderResultModel {
        val findOrderStorageModel = convertAppFindDataModelToStorageFindDataModel(appFindOrderDataModel)
        val resultOrderStorageModel = storage.loadOrderInfo(findOrderStorageModel)
        return convertStorageFindResultModelToAppFindResultModel(resultOrderStorageModel)
    }
    
    private fun convertAppFindDataModelToStorageFindDataModel(fdm: AppFindOrderDataModel): OrderStorageFindDataModel {
        return OrderStorageFindDataModel(trackNumber = fdm.orderOrTrackNum, phoneNumber = fdm.phoneNum?:"")
    }

    private fun convertStorageFindResultModelToAppFindResultModel(storageResult: OrderStorageFindResultModel): AppFindOrderResultModel{
        return AppFindOrderResultModel(customerNumber = storageResult.customerNumber, errorFromJson = storageResult.errorFromJson)
    }
}