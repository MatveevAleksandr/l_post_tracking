package com.example.l_post_tracking.presentation_compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.l_post_tracking.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun WelcomeScreen() {
    val visible = remember { mutableStateOf(true) }
    val progress = remember { mutableStateOf(0.0f) }
//    val scope = rememberCoroutineScope()
//    scope.launch {
//        while (progress.value < 1f) {
//            progress.value += 0.1f
//            delay(1000L)
//        }
//    }

    Column(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(red = 91, green = 37, blue = 153),
                        Color(red = 0, green = 222, blue = 233)
                    )
                )
            )
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally

    ) {
        AnimatedVisibility(
            visible = visible.value, enter = fadeIn(
                initialAlpha = 0f, animationSpec = tween(durationMillis = 2900)
            )

        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome_page_logo),
                contentDescription = null,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 120.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        LinearProgressIndicator(
            progress = progress.value, modifier = Modifier.padding(bottom = 100.dp)
        )
    }
}