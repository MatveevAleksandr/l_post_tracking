package com.example.l_post_tracking.repository

import com.example.l_post_tracking.model.FindOrderStorageModel
import com.example.l_post_tracking.model.ResultOrderStorageModel
import com.example.l_post_tracking.storage.OrderStorage
import com.example.l_post_tracking.model.FindDataModel
import com.example.l_post_tracking.model.FindResultModel

class OrderRepositoryImpl(private val storage: OrderStorage): OrderRepository {
    override fun loadOrder(findDataModel: FindDataModel): FindResultModel {
        val findOrderStorageModel = convertFindDataModelToFindOrderStorageModel(findDataModel)
        val resultOrderStorageModel = storage.loadOrderInfo(findOrderStorageModel)
        return convertResultOrderStorageModelToFindResultModel(resultOrderStorageModel)
    }
    
    private fun convertFindDataModelToFindOrderStorageModel(fdm: FindDataModel): FindOrderStorageModel {
        return FindOrderStorageModel(trackNumber = fdm.orderOrTrackNum, phoneNumber = fdm.phoneNum)
    }

    private fun convertResultOrderStorageModelToFindResultModel(storageResult: ResultOrderStorageModel): FindResultModel{
        return FindResultModel(customerNumber = storageResult.customerNumber)
    }
}