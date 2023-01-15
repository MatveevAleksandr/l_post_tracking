package com.example.l_post_tracking.repository

import com.example.l_post_tracking.model.FindDataModel
import com.example.l_post_tracking.model.FindResultModel

interface OrderRepository {
    fun loadOrder(findDataModel: FindDataModel): FindResultModel
}