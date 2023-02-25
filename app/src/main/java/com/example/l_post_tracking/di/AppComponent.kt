package com.example.l_post_tracking.di

import android.content.Context
import com.example.l_post_tracking.presentation.MainActivityImpl
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {
     fun injectMainActivity(mainActivity: MainActivityImpl)
    @Component.Factory
    interface AppComponentFactory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}