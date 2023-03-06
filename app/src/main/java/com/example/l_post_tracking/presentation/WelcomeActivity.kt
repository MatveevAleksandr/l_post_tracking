package com.example.l_post_tracking.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.l_post_tracking.presentation.theme.TrackingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scope = CoroutineScope(Dispatchers.Main)
        setContent {
            TrackingTheme {
                WelcomeScreen()
            }
        }
        scope.launch {
            delay(2500L)
            startActivity(Intent(applicationContext, MainActivityImpl::class.java))
            finish()
        }
    }
}