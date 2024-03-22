package com.sensorfields.chore.android.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.sensorfields.chore.android.data.realm.entities.ChoreEntity
import com.sensorfields.chore.android.domain.RealmRule
import io.realm.kotlin.ext.query
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreateChoreUseCaseTest {

    @get:Rule
    val realmRule: RealmRule = RealmRule()
    private lateinit var createChoreUseCase: CreateChoreUseCase

    @Before
    fun before() {
        createChoreUseCase = CreateChoreUseCase(realm = realmRule.realm)
    }

    @Test
    fun `add one chore`() = runTest {
        assertThat(realmRule.realm.query<ChoreEntity>().find())
            .isEmpty()

        createChoreUseCase(name = "something", date = null)

        val entities = realmRule.realm.query<ChoreEntity>().find()

        assertThat(entities)
            .hasSize(1)
        with(entities.first()) {
            assertThat(name).isEqualTo("something")
            assertThat(date).isNull()
        }
    }
}
