package com.example.l_post_tracking.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.l_post_tracking.R

@Composable
fun WelcomeScreen() {
    //    Log.e("AAA_AAA", "WelcomeScreen $visible")

    val animationVisible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!animationVisible.value) {
            animationVisible.value = true
        }
    }

    Column(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        MaterialTheme.colors.primary, MaterialTheme.colors.secondary
                    )
                )
            )
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally

    ) {
        AnimatedVisibility(
            visible = animationVisible.value,
            enter = fadeIn(animationSpec = tween(durationMillis = 2500)),
            exit = fadeOut(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome_page_logo),
                contentDescription = null,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 120.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        LinearProgressIndicator(
            modifier = Modifier.padding(bottom = 100.dp), MaterialTheme.colors.secondary
        )
    }
}