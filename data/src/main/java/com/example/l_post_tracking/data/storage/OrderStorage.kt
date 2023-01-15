package com.example.l_post_tracking.data.storage

import com.example.l_post_tracking.data.model.FindOrderStorageModel
import com.example.l_post_tracking.data.model.ResultOrderStorageModel

interface OrderStorage {
    fun loadOrder(findOrderStorageModel: FindOrderStorageModel): ResultOrderStorageModel
}