package com.example.l_post_tracking.presentation_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scope = CoroutineScope(Dispatchers.Main)
        val vv = mutableStateOf(false)
        setContent {
            LaunchedEffect(key1 = Unit, block = {
                delay(600L)
                vv.value = true
            })
            WelcomeScreen(vv.value)
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