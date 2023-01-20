package com.example.l_post_tracking.usecase

import com.example.l_post_tracking.model.AppFindOrderDataModel
import com.example.l_post_tracking.model.AppFindOrderResultModel
import com.example.l_post_tracking.repository.OrderRepository

class FindByOrderOrTrackNumUseCase(private val orderRepository: OrderRepository) {
    fun exec(appFindOrderDataModel: AppFindOrderDataModel): AppFindOrderResultModel{
        return orderRepository.loadOrder(appFindOrderDataModel = appFindOrderDataModel)
    }
}