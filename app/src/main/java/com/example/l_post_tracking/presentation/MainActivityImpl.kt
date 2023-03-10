package com.example.l_post_tracking.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.lifecycle.ViewModelProvider
import com.example.l_post_tracking.app.App
import com.example.l_post_tracking.presentation.theme.TrackingTheme
import com.example.l_post_tracking.viewmodel.MainViewModel
import com.example.l_post_tracking.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainActivityImpl : ComponentActivity() {
    @Inject
    lateinit var vmFactory: MainViewModelFactory
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as App).appComponent.injectMainActivity(this)
        vm = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]

        setContent {
            TrackingTheme(isDarkTheme = isSystemInDarkTheme()) {
                MainScreen(vm)
            }
        }
    }
}




