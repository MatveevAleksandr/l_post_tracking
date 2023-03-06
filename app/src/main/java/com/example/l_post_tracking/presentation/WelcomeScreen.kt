package com.example.l_post_tracking.presentation

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.l_post_tracking.R

@Composable
fun WelcomeScreen() {

    val visible = remember { mutableStateOf(true) }

//    Log.e("AAA_AAA", "WelcomeScreen $visible")
    Column(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        MaterialTheme.colors.primary,
                        MaterialTheme.colors.secondary
                    )
                )
            )
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally

    ) {
        AnimatedVisibility(
            visibleState = remember { MutableTransitionState(true) },
            enter = fadeIn(animationSpec = tween(durationMillis = 7000)),
            exit = fadeOut(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome_page_logo),
                contentDescription = null,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 120.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
//        Button(onClick = { visible.value = !visible.value }){}
        LinearProgressIndicator(
            modifier = Modifier.padding(bottom = 100.dp),
            MaterialTheme.colors.secondary
        )
    }
}