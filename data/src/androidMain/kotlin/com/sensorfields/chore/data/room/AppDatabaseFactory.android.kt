package com.sensorfields.chore.data.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject

@Inject
@ContributesBinding(AppScope::class)
public class AndroidAppDatabaseBuilderFactory(
    private val context: Context,
) : AppDatabaseBuilderFactory {

    override fun invoke(name: String): RoomDatabase.Builder<AppDatabase> {
        return Room.databaseBuilder(
            context = context,
            name = name,
            factory = AppDatabaseConstructor::initialize,
        )
    }
}
