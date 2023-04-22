package com.example.foodomer

import android.app.Application

class MainApplication: Application() {

    lateinit var container: IAppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}