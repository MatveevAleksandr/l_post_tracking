package com.example.l_post_tracking.data.repository

import com.example.l_post_tracking.data.storage.OrderStorage
import com.example.l_post_tracking.model.FindDataModel
import com.example.l_post_tracking.model.FindResultModel
import com.example.l_post_tracking.repository.OrderRepository

class OrderRepositoryImpl(private val storage: OrderStorage): OrderRepository {
    override fun loadOrder(findDataModel: FindDataModel): FindResultModel {
        return FindResultModel(0)
    }
}