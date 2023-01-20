package com.example.l_post_tracking.usecase

import com.example.l_post_tracking.model.AppFindOrderDataModel
import com.example.l_post_tracking.model.AppFindOrderResultModel
import com.example.l_post_tracking.repository.OrderRepository

class FindByPhoneNumUseCase(orderRepository: OrderRepository) {
    fun exec(appFindOrderDataModel: AppFindOrderDataModel): AppFindOrderResultModel {
        return AppFindOrderResultModel(customerNumber = null, errorFromJson = null)
    }
}