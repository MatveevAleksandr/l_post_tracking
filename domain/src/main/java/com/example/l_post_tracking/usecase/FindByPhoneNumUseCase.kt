package com.example.l_post_tracking.usecase

import com.example.l_post_tracking.model.FindDataModel
import com.example.l_post_tracking.model.FindResultModel
import com.example.l_post_tracking.repository.OrderRepository

class FindByPhoneNumUseCase(orderRepository: OrderRepository) {
    fun exec(findDataModel: FindDataModel): FindResultModel {
        return FindResultModel(customerNumber = null)
    }
}