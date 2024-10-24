package com.sensorfields.chore.android.data.room.test

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sensorfields.chore.android.data.room.ApplicationDatabase
import com.sensorfields.chore.android.data.room.ChoreDao
import org.junit.rules.ExternalResource

public class ApplicationDatabaseRule : ExternalResource() {

    public val choreDao: ChoreDao
        get() = applicationDatabase.choreDao()

    private lateinit var applicationDatabase: ApplicationDatabase

    override fun before() {
        applicationDatabase = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                ApplicationDatabase::class.java,
            )
            .build()
    }

    override fun after() {
        applicationDatabase.close()
    }
}
