package com.example.l_post_tracking.storage

import com.example.l_post_tracking.model.FindOrderStorageModel
import com.example.l_post_tracking.model.ResultOrderStorageModel

interface OrderStorage {
    fun loadOrderInfo(findOrderStorageModel: FindOrderStorageModel): ResultOrderStorageModel
}