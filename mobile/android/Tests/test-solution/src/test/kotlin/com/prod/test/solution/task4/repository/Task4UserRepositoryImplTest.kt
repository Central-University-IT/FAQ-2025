package com.prod.test.solution.task4.repository

import com.prod.core.api.data.sources.remote.UserRemoteDataSource
import com.prod.core.api.domain.models.UserInfo
import com.prod.solution.impl.domain.repositories.UserRepositoryImpl
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class Task4UserRepositoryImplTest {

    @Test
    fun userRepositoryImpl_returns_remote_data_when_getUserInfo_score_1() {
        val remoteDataSource = mock<UserRemoteDataSource> {
            on { getUserInfo() } doReturn userInfo
        }
        val repository = UserRepositoryImpl(
            userRemoteDataSource = remoteDataSource
        )

        val actual = repository.getUserInfo()

        assertEquals(userInfo, actual)
        verify(remoteDataSource, times(1)).getUserInfo()
    }

    private companion object {
        private val userInfo = UserInfo(
            lastGoodsCategories = listOf("FRUITS", "MILK"),
            availableBonuses = listOf("bonus_323", "bonus_4243", "bonus_13132"),
            favorites = listOf("good_13643463-haerq"),
            activityLevel = 77
        )
    }
}
