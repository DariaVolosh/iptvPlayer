package com.example.iptvplayer

import android.app.Application
import com.example.iptvplayer.di.ApplicationComponent
import com.example.iptvplayer.di.DaggerApplicationComponent

class MyApp: Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().build()
    }
}