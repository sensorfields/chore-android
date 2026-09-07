package com.sensorfields.chore.data

import com.sensorfields.chore.data.room.AppDatabase
import com.sensorfields.chore.data.room.AppDatabaseBuilderFactory
import com.sensorfields.chore.data.room.ChoreDao
import com.sensorfields.chore.data.room.createAppDatabase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn

@BindingContainer
@ContributesTo(AppScope::class)
public object DataBindings {

    @Provides
    @SingleIn(AppScope::class)
    public fun appDatabase(
        appDatabaseBuilderFactory: AppDatabaseBuilderFactory,
    ): AppDatabase = createAppDatabase(factory = appDatabaseBuilderFactory)

    @Provides
    @SingleIn(AppScope::class)
    public fun choreDao(appDatabase: AppDatabase): ChoreDao = appDatabase.choreDao()
}
