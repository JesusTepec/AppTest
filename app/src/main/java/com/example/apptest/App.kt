package com.example.apptest

import android.app.Application
import di.AppComponent
import di.AppRepositoryModule
import di.DaggerAppComponent

class App : Application() {

    lateinit var appCompoment: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appCompoment = DaggerAppComponent.builder()
            .appRepositoryModule(AppRepositoryModule(this))
            .build()
    }
}