package com.example.l_post_tracking.app

import android.app.Application
import com.example.l_post_tracking.di.appModule
import com.example.l_post_tracking.di.dataModule
import com.example.l_post_tracking.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}