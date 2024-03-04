package com.example.testgameapp

import android.app.Application
import com.example.testgameapp.di.dataModule
import com.example.testgameapp.di.domainModule
import com.example.testgameapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(presentationModule, domainModule, dataModule))
        }
    }
}