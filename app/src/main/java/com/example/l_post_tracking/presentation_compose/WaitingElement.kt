package com.example.l_post_tracking.presentation_compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun WaitingElement(){
    Log.e("AAA_AAA", "WaitingElement")
    Box(
        modifier = Modifier.background(Color.White, shape = RoundedCornerShape(15.dp)).width(100.dp).height(100.dp)
    ) {
        CircularProgressIndicator()
    }
}