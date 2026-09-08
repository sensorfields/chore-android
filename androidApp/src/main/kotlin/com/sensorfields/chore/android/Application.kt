package com.sensorfields.chore.android

import dev.zacsweers.metro.createGraphFactory

class Application : android.app.Application() {

    val appGraph by lazy {
        createGraphFactory<AndroidAppGraph.Factory>().create(context = this)
    }
}
