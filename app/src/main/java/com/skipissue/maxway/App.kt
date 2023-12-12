package com.skipissue.maxway

import android.app.Application
import android.content.Context
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
        MapKitFactory.setApiKey("58099092-9143-4132-a9e9-c68efd2ddfbd")
        MapKitFactory.initialize(context)
    }
    companion object{
        lateinit var context: Context
    }
}