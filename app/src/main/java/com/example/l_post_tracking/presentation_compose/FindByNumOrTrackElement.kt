package com.example.l_post_tracking.presentation_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun FindByNumOrTrackElement() {
    Box(
        modifier = Modifier.background(Color.White, shape = RoundedCornerShape(15.dp))
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
            OutlinedTextField(value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                placeholder = { Text(text = "Номер или трек номер отправления") })
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Найти")
            }
        }
    }
}