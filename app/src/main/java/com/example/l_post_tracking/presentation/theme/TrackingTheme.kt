package com.example.l_post_tracking.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightColorPallet = lightColors(
    error = Color(0xFFAE0303),
    onPrimary = Color.White
)

private val darkColorPallet = darkColors(
    primary = Color(0xFF4946A4),
    secondary = Color(0xFF26A69A),
    error = Color(0xFF943535),
    onPrimary = Color.LightGray
)

@Composable
fun TrackingTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorPallet = if (isDarkTheme) {
        darkColorPallet
    } else {
        lightColorPallet
    }

    MaterialTheme(
        colors = colorPallet, content = content
    )
}