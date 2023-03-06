package com.example.l_post_tracking.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FindByPhoneElement(orderNumber: String, txtError: String?, onFindClick: (String) -> Unit = {}) {

    Log.e("AAA_AAA", "FindByPhoneElement")

    val txtEditState = remember { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(20.dp), elevation = 20.dp
    ) {
        Column(
            horizontalAlignment = Alignment.Start, modifier = Modifier.padding(15.dp)
        ) {
            Text(text = "№ $orderNumber", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "Пожалуйста, уточните номер телефона получателя этого заказа",
                fontSize = 15.sp,
                color = Color.Gray
            )
            OutlinedTextField(
                value = txtEditState.value,
                onValueChange = { txtEditState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                placeholder = { Text(text = "Номер телефона") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            if (!txtError.isNullOrEmpty()) {
                Text(text = txtError, color = Color(red = 174, green = 3, blue = 3))
            }
            Button(
                onClick = { onFindClick(txtEditState.value) }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Найти")
            }
        }
    }

}