package com.example.l_post_tracking.repository

import com.example.l_post_tracking.model.AppFindOrderDataModel
import com.example.l_post_tracking.model.AppFindOrderResultState

interface OrderRepository {
    fun loadOrder(appFindOrderDataModel: AppFindOrderDataModel): AppFindOrderResultState
}