package com.prod.test.solution.task4.remote

import com.prod.core.api.data.json.JsonProvider
import com.prod.core.api.domain.models.UserInfo
import com.prod.solution.impl.data.sources.remote.UserRemoteDataSourceImpl
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class Task4UserRemoteDataSourceImplTest {

    @Test
    fun jsonProvider_called_when_data_source_called_score_1() {
        val jsonProviderMock = mock<JsonProvider> {
            on { userInfo } doReturn """
                {
                    "last_goods_cat": ["FRUITS", "MILK"],
                    "available_bonuses": ["bonus_323", "bonus_4243", "bonus_13132"],
                    "favourites": ["good_13643463-hae"],
                    "activity_level": 50
                }
            """.trimIndent()
        }
        val userRemoteDataSource = UserRemoteDataSourceImpl(jsonProviderMock)

        userRemoteDataSource.getUserInfo()

        verify(jsonProviderMock).userInfo
    }

    @Test
    fun data_source_returns_model_with_full_info_when_JSON_is_full_score_1() {
        val jsonProviderMock = mock<JsonProvider> {
            on { userInfo } doReturn """
                {
                    "last_goods_cat": ["FRUITS", "MILK"],
                    "available_bonuses": ["bonus_323", "bonus_4243", "bonus_13132"],
                    "favourites": ["good_13643463-hae"],
                    "activity_level": 50
                }
            """.trimIndent()
        }
        val userRemoteDataSource = UserRemoteDataSourceImpl(jsonProviderMock)

        val userInfo = userRemoteDataSource.getUserInfo()

        assertEquals(
            UserInfo(
                lastGoodsCategories = listOf("FRUITS", "MILK"),
                availableBonuses = listOf("bonus_323", "bonus_4243", "bonus_13132"),
                favorites = listOf("good_13643463-hae"),
                activityLevel = 50
            ),
            userInfo
        )
    }

    @Test
    fun data_source_returns_model_with_empty_lastGoodsCategories_when_JSON_last_goods_cat_is_empty_score_1() {
        val jsonProviderMock = mock<JsonProvider> {
            on { userInfo } doReturn """
                {
                    "last_goods_cat": [],
                    "available_bonuses": ["bonus_323", "bonus_4243", "bonus_13132"],
                    "favourites": ["good_13643463-hae"],
                    "activity_level": 50
                }
            """.trimIndent()
        }
        val userRemoteDataSource = UserRemoteDataSourceImpl(jsonProviderMock)

        val userInfo = userRemoteDataSource.getUserInfo()

        assertEquals(
            UserInfo(
                lastGoodsCategories = listOf(),
                availableBonuses = listOf("bonus_323", "bonus_4243", "bonus_13132"),
                favorites = listOf("good_13643463-hae"),
                activityLevel = 50
            ),
            userInfo
        )
    }

    @Test
    fun data_source_returns_model_with_empty_availableBonuses_when_JSON_available_bonuses_is_empty_score_1() {
        val jsonProviderMock = mock<JsonProvider> {
            on { userInfo } doReturn """
                {
                    "last_goods_cat": ["FRUITS", "MILK"],
                    "available_bonuses": [],
                    "favourites": ["good_13643463-hae"],
                    "activity_level": 30
                }
            """.trimIndent()
        }
        val userRemoteDataSource = UserRemoteDataSourceImpl(jsonProviderMock)

        val userInfo = userRemoteDataSource.getUserInfo()

        assertEquals(
            UserInfo(
                lastGoodsCategories = listOf("FRUITS", "MILK"),
                availableBonuses = listOf(),
                favorites = listOf("good_13643463-hae"),
                activityLevel = 30
            ),
            userInfo
        )
    }

    @Test
    fun data_source_returns_model_with_empty_favorites_when_JSON_favourites_is_empty_score_1() {
        val jsonProviderMock = mock<JsonProvider> {
            on { userInfo } doReturn """
                {
                    "last_goods_cat": ["FRUITS", "MILK"],
                    "available_bonuses": ["bonus_323", "bonus_4243", "bonus_13132"],
                    "favourites": [],
                    "activity_level": -10
                }
            """.trimIndent()
        }
        val userRemoteDataSource = UserRemoteDataSourceImpl(jsonProviderMock)

        val userInfo = userRemoteDataSource.getUserInfo()

        assertEquals(
            UserInfo(
                lastGoodsCategories = listOf("FRUITS", "MILK"),
                availableBonuses = listOf("bonus_323", "bonus_4243", "bonus_13132"),
                favorites = listOf(),
                activityLevel = -10
            ),
            userInfo
        )
    }
}
