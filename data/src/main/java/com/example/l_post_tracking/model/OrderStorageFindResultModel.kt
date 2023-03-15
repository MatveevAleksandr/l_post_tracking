package com.example.l_post_tracking.model

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class OrderStorageFindResultModel(
    val isDataLoaded: Boolean = false,
    val errorMessage: String? = null,
    val isNeedAddPhoneNum: Boolean = false,
    val customerNumber: String? = null,
    val orderNumber: String? = null,
    val orderType: Int? = null,
    val statusDescription: String? = null,
    val deliveryDatePlan: String? = null,
    val pvzAddress: String? = null,
    val pvzAddressDop: String? = null,
    val timeFrom: String? = null,
    val timeTo: String? = null,
    val isCourier: Boolean? = null,
    val canPayCard: Boolean? = null,
    val canPayCash: Boolean? = null
)

fun OrderStorageFindResultModel.asAppFindResultModel(): AppFindOrderResultState {
    val storageResult = this
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