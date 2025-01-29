package com.prod.test.solution.task4.repository

import com.prod.core.api.data.sources.local.BonusesLocalDataSource
import com.prod.core.api.data.sources.remote.BonusesRemoteDataSource
import com.prod.core.api.domain.models.BonusInfo
import com.prod.solution.impl.domain.repositories.BonusesRepositoryImpl
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import java.util.Date

class Task4BonusesRepositoryImplTest {

    @Test
    fun bonusesRepositoryImpl_returns_cache_when_getAllBonuses_if_cache_has_list_with_1_item_score_2() {
        val localDataSource = mock<BonusesLocalDataSource> {
            on { getCachedBonuses() } doReturn listOf(bonusInfo1)
        }
        val remoteDataSource = mock<BonusesRemoteDataSource>()
        val repository = BonusesRepositoryImpl(
            bonusesLocalDataSource = localDataSource,
            bonusesRemoteDataSource = remoteDataSource,
        )

        val actual = repository.getAllBonuses()

        assertEquals(listOf(bonusInfo1), actual)
        verify(localDataSource, times(1)).getCachedBonuses()
        verifyNoInteractions(remoteDataSource)
    }

    @Test
    fun bonusesRepositoryImpl_returns_cache_when_getAllBonuses_if_cache_has_list_with_2_item_score_1() {
        val localDataSource = mock<BonusesLocalDataSource> {
            on { getCachedBonuses() } doReturn listOf(bonusInfo1, bonusInfo2)
        }
        val remoteDataSource = mock<BonusesRemoteDataSource>()
        val repository = BonusesRepositoryImpl(
            bonusesLocalDataSource = localDataSource,
            bonusesRemoteDataSource = remoteDataSource,
        )

        val actual = repository.getAllBonuses()

        assertEquals(listOf(bonusInfo1, bonusInfo2), actual)
        verify(localDataSource, times(1)).getCachedBonuses()
        verifyNoInteractions(remoteDataSource)
    }

    @Test
    fun bonusesRepositoryImpl_returns_remote_data__and_cache_when_getAllBonuses_if_cache_is_empty_score_1() {
        val localDataSource = mock<BonusesLocalDataSource> {
            on { getCachedBonuses() } doReturn listOf()
        }
        val remoteDataSource = mock<BonusesRemoteDataSource> {
            on { getAllBonuses() } doReturn listOf(bonusInfo1, bonusInfo2)
        }
        val repository = BonusesRepositoryImpl(
            bonusesLocalDataSource = localDataSource,
            bonusesRemoteDataSource = remoteDataSource,
        )

        val actual = repository.getAllBonuses()

        assertEquals(listOf(bonusInfo1, bonusInfo2), actual)
        verify(localDataSource).getCachedBonuses()
        verify(localDataSource).cacheBonuses(eq(listOf(bonusInfo1, bonusInfo2)))
        verify(remoteDataSource).getAllBonuses()
    }

    private companion object {
        private val bonusInfo1 = BonusInfo(
            id = "id1",
            type = "type1",
            value = 222.22,
            availableDueTo = Date(),
            promotionExtra = null,
        )
        private val bonusInfo2 = BonusInfo(
            id = "id2",
            type = "type2",
            value = 111.11,
            availableDueTo = null,
            promotionExtra = null,
        )
    }
}
