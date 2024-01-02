package com.example.metmuseum

import android.app.Application
import com.example.metmuseum.data.AppContainer
import com.example.metmuseum.data.DefaultAppContainer

class Application : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}