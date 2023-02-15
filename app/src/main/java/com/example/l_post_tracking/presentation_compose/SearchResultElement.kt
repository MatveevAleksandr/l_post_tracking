package com.example.l_post_tracking.presentation_compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.l_post_tracking.R
import com.example.l_post_tracking.model.AppFindOrderResultDataModel

private val ICON_SIDE_SIZE = 30.dp

@Composable
fun SearchResultElement(
    orderData: AppFindOrderResultDataModel, onAddressClick: (address: String) -> Unit
) {

    Log.e("AAA_AAA", "SearchResultElement")

    Box(
        modifier = Modifier.background(Color.White, shape = RoundedCornerShape(15.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .verticalScroll(state = rememberScrollState(0))
        ) {
            Text(
                text = "Заказ № ${orderData.orderNumber} ${if (orderData.isCourier) "Курьерская доставка" else "Самовывоз"}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            StatusRow(status = orderData.statusDescription ?: "")
            Spacer(modifier = Modifier.height(10.dp))
            DateDeliveryRow(
                deliveryDate = orderData.deliveryDatePlan,
                timeFrom = orderData.timeFrom,
                timeTo = orderData.timeTo
            )
            if (!orderData.isCourier) {
                Spacer(modifier = Modifier.height(10.dp))
                AddressRow(
                    address = "${orderData.pvzAddress} \n ${orderData.pvzAddressDop}",
                    onAddressClick = onAddressClick
                )
                Spacer(modifier = Modifier.height(10.dp))
                PaymentTypeRow(canPayCash = orderData.canPayCash, canPayCard = orderData.canPayCard)
            }
        }
    }
}

@Composable
fun StatusRow(status: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.state),
            contentDescription = null,
            modifier = Modifier.size(
                ICON_SIDE_SIZE
            )
        )
        Text(text = "Статус заказа", fontSize = 15.sp, modifier = Modifier.padding(start = 5.dp))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = status, textAlign = TextAlign.End)
    }
}


@Composable
fun DateDeliveryRow(deliveryDate: String?, timeFrom: String?, timeTo: String?) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.delivery_date),
            contentDescription = null,
            modifier = Modifier.size(
                ICON_SIDE_SIZE
            )
        )
        Text(
            text = "Планируемая \nдата доставки",
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 5.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        if (deliveryDate.isNullOrEmpty()) {
            Text(text = "Дата будет известна позднее")
        } else {
            Text(
                text = "$deliveryDate ${if (timeFrom.isNullOrEmpty() || timeTo.isNullOrEmpty()) "" else "С ${timeFrom}:00 до ${timeTo}:00"}",
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
fun AddressRow(address: String, onAddressClick: (address: String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.address),
            contentDescription = null,
            modifier = Modifier.size(
                ICON_SIDE_SIZE
            )
        )
        Text(text = "Адрес", fontSize = 15.sp, modifier = Modifier.padding(start = 5.dp))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = address,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.End,
            modifier = Modifier.clickable { onAddressClick(address) })
    }
}

@Composable
fun PaymentTypeRow(canPayCash: Boolean, canPayCard: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.payment_mode),
            contentDescription = null,
            modifier = Modifier.size(
                ICON_SIDE_SIZE
            )
        )
        Text(
            text = "${if (canPayCash) "Оплата наличными" else ""} ${if (canPayCard) "или картой" else ""}",
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}