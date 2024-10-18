package com.sensorfields.chore.android.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.sensorfields.chore.android.data.room.entities.ChoreEntity
import com.sensorfields.chore.android.data.realm.test.RealmRule
import com.sensorfields.chore.android.domain.mappers.toObjectId
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

        val chore = createChoreUseCase(name = "something", date = null)

        val entities = realmRule.realm.query<ChoreEntity>().find()

        assertThat(entities)
            .hasSize(1)
        with(entities.first()) {
            assertThat(id).isEqualTo(chore.getOrThrow().id.toObjectId())
            assertThat(name).isEqualTo("something")
            assertThat(date).isNull()
        }
    }
}
