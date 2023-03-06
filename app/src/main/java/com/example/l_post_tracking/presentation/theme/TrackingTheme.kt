package com.example.l_post_tracking.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightColorPallet = lightColors(
    error = Color(red = 174, green = 3, blue = 3),
)

private val darkColorPallet = darkColors(
    primary = Color(red = 73, green = 70, blue = 164, alpha = 255),
    secondary = Color(0xFF26A69A),
    error = Color(red = 148, green = 53, blue = 53, alpha = 255)
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