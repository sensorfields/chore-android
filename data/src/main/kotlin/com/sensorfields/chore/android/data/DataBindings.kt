package com.sensorfields.chore.android.data

import android.content.Context
import androidx.room.Room
import com.sensorfields.chore.android.data.room.ApplicationDatabase
import com.sensorfields.chore.android.data.room.ChoreDao
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn

@BindingContainer
public object DataBindings {

    @Provides
    @SingleIn(AppScope::class)
    public fun applicationDatabase(context: Context): ApplicationDatabase {
        return Room.databaseBuilder(context, ApplicationDatabase::class.java, "app.db")
            // TODO setup logging in debug mode
            .build()
    }

    @Provides
    @SingleIn(AppScope::class)
    public fun choreDao(applicationDatabase: ApplicationDatabase): ChoreDao {
        return applicationDatabase.choreDao()
    }
}
