package com.example.l_post_tracking.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@Preview(showBackground = true)
@Composable
fun FindByNumOrTrackElement(
    txtError: String? = null, onFindClick: (String) -> Unit = {}
) {

//    Log.e("AAA_AAA", "FindByNumOrTrackElement")

    val txtEditState = remember { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(20.dp), elevation = 20.dp
    ) {
        Column(
            horizontalAlignment = Alignment.Start, modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = "Найти отправление", fontSize = 25.sp, fontWeight = FontWeight.Bold
            )
            Text(
                text = "Узнайте статус и способ получения заказа службы доставки Л-Пост",
                fontSize = 15.sp,
                color = Color.Gray
            )
            OutlinedTextField(value = txtEditState.value,
                onValueChange = { txtEditState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                placeholder = { Text(text = "Номер или трек номер отправления") })
            if (!txtError.isNullOrEmpty()) {
                Text(text = txtError, color = MaterialTheme.colors.error)
            }
            Button(
                onClick = { onFindClick(txtEditState.value) }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Найти")
            }
        }
    }
}