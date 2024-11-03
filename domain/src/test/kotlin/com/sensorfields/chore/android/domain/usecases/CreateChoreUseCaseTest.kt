package com.sensorfields.chore.android.domain.usecases

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sensorfields.chore.android.data.room.ChoreDao
import com.sensorfields.chore.android.data.room.entities.ChoreEntity
import com.sensorfields.chore.android.data.room.test.ApplicationDatabaseRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateChoreUseCaseTest {

    @get:Rule
    val applicationDatabaseRule = ApplicationDatabaseRule()
    private lateinit var choreDao: ChoreDao

    private lateinit var createChoreUseCase: CreateChoreUseCase

    @Before
    fun before() {
        choreDao = applicationDatabaseRule.choreDao
        createChoreUseCase = CreateChoreUseCase(choreDao = choreDao)
    }

    @Test
    fun `add one chore`() = runTest {
        val chores = choreDao.find("name", isAscending = true)

        assertThat(chores.first())
            .isEmpty()

        val chore = createChoreUseCase(name = "something", date = null)

        assertThat(chores.first())
            .containsExactly(
                ChoreEntity(
                    id = chore.getOrThrow().id.value,
                    name = "something",
                    date = null,
                ),
            )
    }
}
