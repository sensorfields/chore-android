package com.sensorfields.chore.data.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.sensorfields.chore.data.room.entities.ChoreEntity

@Database(entities = [ChoreEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
public abstract class AppDatabase : RoomDatabase() {
    public abstract fun choreDao(): ChoreDao
}
