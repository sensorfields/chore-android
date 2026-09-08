package com.sensorfields.chore.data.room

import androidx.room.Room
import androidx.room.RoomDatabase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@Inject
@ContributesBinding(AppScope::class)
public class IosAppDatabaseBuilderFactory : AppDatabaseBuilderFactory {

    override fun invoke(name: String): RoomDatabase.Builder<AppDatabase> {
        return Room.databaseBuilder<AppDatabase>(
            name = documentDirectory() + "/" + name,
            factory = AppDatabaseConstructor::initialize,
        )
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}
