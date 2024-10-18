package com.sensorfields.chore.android.data

import android.content.Context
import androidx.room.Room
import com.sensorfields.chore.android.data.room.ApplicationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Singleton
    @Provides
    fun applicationDatabase(@ApplicationContext context: Context): ApplicationDatabase {
        return Room.databaseBuilder(context, ApplicationDatabase::class.java, "app.db")
            .build()
    }

    @Singleton
    @Provides
    fun choreDao(applicationDatabase: ApplicationDatabase) = applicationDatabase.choreDao()
}
