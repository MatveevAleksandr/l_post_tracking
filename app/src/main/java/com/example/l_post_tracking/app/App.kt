package com.example.l_post_tracking.app

import android.app.Application
import com.example.l_post_tracking.di.AppComponent
import com.example.l_post_tracking.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}