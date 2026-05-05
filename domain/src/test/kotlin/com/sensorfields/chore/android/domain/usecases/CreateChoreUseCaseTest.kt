package com.sensorfields.chore.android.domain.usecases

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sensorfields.chore.android.data.room.ChoreDao
import com.sensorfields.chore.android.data.room.entities.ChoreEntity
import com.sensorfields.chore.android.data.room.test.ApplicationDatabaseRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
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
        val chores = mutableListOf<List<ChoreEntity>>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            choreDao.find("name", isAscending = true).toList(chores)
        }

        assertThat(chores.first())
            .isEmpty()

        val chore = createChoreUseCase(name = "something", date = null)

        assertThat(chores.last())
            .containsExactly(
                ChoreEntity(
                    id = chore.getOrThrow().id.value,
                    name = "something",
                    date = null,
                ),
            )
    }
}
