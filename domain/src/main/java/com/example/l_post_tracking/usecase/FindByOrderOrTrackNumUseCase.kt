package com.example.l_post_tracking.usecase

import com.example.l_post_tracking.model.FindDataModel
import com.example.l_post_tracking.model.FindResultModel
import com.example.l_post_tracking.repository.OrderRepository

class FindByOrderOrTrackNumUseCase(private val orderRepository: OrderRepository) {
    fun exec(findDataModel: FindDataModel): FindResultModel{
        return orderRepository.loadOrder(findDataModel = findDataModel)
    }
}