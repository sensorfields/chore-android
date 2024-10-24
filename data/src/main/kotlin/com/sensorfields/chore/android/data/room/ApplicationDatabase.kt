package com.sensorfields.chore.android.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sensorfields.chore.android.data.room.entities.ChoreEntity

/**
 * TODO Public only because of test rule
 */
@Database(entities = [ChoreEntity::class], version = 1)
public abstract class ApplicationDatabase : RoomDatabase() {
    public abstract fun choreDao(): ChoreDao
}
