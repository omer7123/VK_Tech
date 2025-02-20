package com.example.vktech

import android.app.Application
import com.example.vktech.di.AppComponent
import com.example.vktech.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}