package com.example.l_post_tracking.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaitingElement() {
//    Log.e("AAA_AAA", "WaitingElement")
    Card(
        shape = RoundedCornerShape(20.dp), elevation = 20.dp
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(75.dp))
        }
    }
}




