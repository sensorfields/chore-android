package com.sensorfields.chore.android

import dagger.hilt.android.HiltAndroidApp
import logcat.AndroidLogcatLogger

@HiltAndroidApp
class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        setupLogcat()
    }

    private fun setupLogcat() {
        AndroidLogcatLogger.installOnDebuggableApp(this)
    }
}
