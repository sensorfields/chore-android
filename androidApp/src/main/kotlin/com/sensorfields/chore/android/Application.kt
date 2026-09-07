package com.sensorfields.chore.android

import dev.zacsweers.metro.createGraphFactory
import logcat.AndroidLogcatLogger

class Application : android.app.Application() {

    val appGraph by lazy {
        createGraphFactory<AndroidAppGraph.Factory>().create(context = this)
    }

    override fun onCreate() {
        super.onCreate()
        setupLogcat()
    }

    private fun setupLogcat() {
        AndroidLogcatLogger.installOnDebuggableApp(this)
    }
}
