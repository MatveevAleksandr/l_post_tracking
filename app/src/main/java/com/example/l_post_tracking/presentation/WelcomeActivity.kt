package com.example.l_post_tracking.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import com.example.l_post_tracking.presentation.theme.TrackingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scope = CoroutineScope(Dispatchers.Main)
        val vv = mutableStateOf(false)
        setContent {
            TrackingTheme {
                WelcomeScreen()
            }
        }
        scope.launch {
            delay(2900L)
            startActivity(Intent(applicationContext, MainActivityImpl::class.java))
            finish()
        }

//        visible.value = false
//        visible.value = true
//        Покажем лого и запустим дальше
//        scope.launch {
//            delay(2900L)
//            startActivity(Intent(applicationContext, MainActivityImpl::class.java))
//        }
    }
}