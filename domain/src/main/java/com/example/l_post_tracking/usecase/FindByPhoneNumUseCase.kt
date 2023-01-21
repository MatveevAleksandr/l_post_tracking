package com.example.l_post_tracking.usecase

import com.example.l_post_tracking.model.AppFindOrderDataModel
import com.example.l_post_tracking.model.AppFindOrderResultDataModel
import com.example.l_post_tracking.model.AppFindOrderResultState
import com.example.l_post_tracking.repository.OrderRepository

class FindByPhoneNumUseCase(private val orderRepository: OrderRepository) {
    fun exec(appFindOrderDataModel: AppFindOrderDataModel): AppFindOrderResultState {
        return orderRepository.loadOrder(appFindOrderDataModel = appFindOrderDataModel)
    }
}