package com.example.l_post_tracking.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.l_post_tracking.R
import com.example.l_post_tracking.presentation_compose.MainActivityImpl

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        findViewById<ImageView>(R.id.welcomeLogo).startAnimation(
            AnimationUtils.loadAnimation(
                this, R.anim.fadein
            )
        )
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivityImpl::class.java))
        }, 2900)
    }
}