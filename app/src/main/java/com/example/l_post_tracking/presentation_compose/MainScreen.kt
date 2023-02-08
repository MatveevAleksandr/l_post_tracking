package com.example.l_post_tracking.presentation_compose

import android.graphics.Color.rgb
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.l_post_tracking.R

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(red = 91, green = 37, blue = 153),
                        Color(red = 0, green = 222, blue = 233)
                    )
                )
            )
            .width(100.dp)
            .height(100.dp)
    ) {
        Row() {
            Image(painter = painterResource(id = R.drawable.main_page_logo), contentDescription = null)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "IIIIII")
            Column() {
                Text(text = "ffffff")
                Button(onClick = {}) {
                    Text(text = "hhhhhhhhh")
                }
            }
        }
    }
}