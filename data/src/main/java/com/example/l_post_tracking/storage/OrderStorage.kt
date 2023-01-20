package com.example.l_post_tracking.storage

import com.example.l_post_tracking.model.OrderStorageFindDataModel
import com.example.l_post_tracking.model.OrderStorageFindResultModel

interface OrderStorage {
    fun loadOrderInfo(orderStorageFindDataModel: OrderStorageFindDataModel): OrderStorageFindResultModel
}