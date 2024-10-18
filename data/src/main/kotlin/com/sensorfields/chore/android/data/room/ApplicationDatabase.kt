package com.sensorfields.chore.android.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sensorfields.chore.android.data.room.entities.ChoreEntity

@Database(entities = [ChoreEntity::class], version = 1)
internal abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun choreDao(): ChoreDao
}
