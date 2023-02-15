package com.example.l_post_tracking.repository

import android.util.Log
import com.example.l_post_tracking.model.*
import com.example.l_post_tracking.storage.OrderStorage
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class OrderRepositoryImpl(private val storage: OrderStorage) : OrderRepository {

    override fun loadOrder(appFindOrderDataModel: AppFindOrderDataModel): AppFindOrderResultState {
        val findOrderStorageModel =
            convertAppFindDataModelToStorageFindDataModel(appFindOrderDataModel)
        val resultOrderStorageModel = storage.loadOrderInfo(findOrderStorageModel)
        return convertStorageFindResultModelToAppFindResultModel(resultOrderStorageModel)
    }

    private fun convertAppFindDataModelToStorageFindDataModel(fdm: AppFindOrderDataModel): OrderStorageFindDataModel {
        return OrderStorageFindDataModel(
            trackNumber = fdm.orderOrTrackNum, phoneNumber = fdm.phoneNum ?: ""
        )
    }

    private fun convertStorageFindResultModelToAppFindResultModel(storageResult: OrderStorageFindResultModel): AppFindOrderResultState {

        val convertedDeliveryDate = if (storageResult.deliveryDatePlan != null) {
            val formattedDate: String = DateFormat.getDateInstance(DateFormat.FULL).format(
                SimpleDateFormat(
                    "yyyy-MM-dd", Locale.US
                ).parse(storageResult.deliveryDatePlan)!!
            )
            val splitDate = formattedDate.split(",").toTypedArray()
            splitDate[1] + " (${splitDate[0]})"
        } else {
            null
        }

        return if (storageResult.isDataLoaded) {
            val data = AppFindOrderResultDataModel(
                customerNumber = storageResult.customerNumber,
                orderNumber = storageResult.orderNumber,
                orderType = storageResult.orderType,
                statusDescription = storageResult.statusDescription,
                deliveryDatePlan = convertedDeliveryDate,
                timeFrom = storageResult.timeFrom,
                timeTo = storageResult.timeTo,
                isCourier = storageResult.isCourier ?: false,
                canPayCash = storageResult.canPayCash ?: false,
                canPayCard = storageResult.canPayCard ?: false,
                pvzAddress = storageResult.pvzAddress,
                pvzAddressDop = storageResult.pvzAddressDop
            )
            DataLoaded(data = data)
        } else if (storageResult.isNeedAddPhoneNum) {
            NeedAddPhoneNumberForSearch
        } else {
            GetError(
                errMessage = storageResult.errorMessage
                    ?: "Ошибка загрузки. Код 103. Обратитесь в поддержку"
            )
        }
    }
}