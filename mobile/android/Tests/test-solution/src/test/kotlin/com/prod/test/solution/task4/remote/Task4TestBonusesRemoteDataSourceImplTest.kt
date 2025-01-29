package com.prod.test.solution.task4.remote

import com.prod.core.api.data.json.JsonProvider
import com.prod.core.api.domain.models.BonusInfo
import com.prod.core.api.domain.models.PromotionExtra
import com.prod.solution.impl.data.sources.remote.BonusesRemoteDataSourceImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Task4TestBonusesRemoteDataSourceImplTest {

    @Test
    fun jsonProvider_called_when_data_source_called_score_1() {
        val jsonProviderMock = mock<JsonProvider> {
            on { allBonusesJson } doReturn "[]"
        }
        val bonusesRemoteDataSource = BonusesRemoteDataSourceImpl(jsonProviderMock)

        val bonusesList = bonusesRemoteDataSource.getAllBonuses()

        assertTrue(bonusesList.isEmpty())
        verify(jsonProviderMock).allBonusesJson
    }

    @Test
    fun data_source_returns_model_with_full_info_when_JSON_is_full_score_1() {
        val jsonProviderMock = mock<JsonProvider> {
            on { allBonusesJson } doReturn """
                [
                    {
                        "id": "bonus_1_1",
                        "type": "points",
                        "value": 200,
                        "available_due_to": "$DATE_STRING",
                        "promotion_extra": {
                            "base_color": "5222FF",
                            "tint_color": "FFFFFF",
                            "label": "PREMIUM"
                        }
                    }
                ]
            """.trimIndent()
        }
        val bonusesRemoteDataSource = BonusesRemoteDataSourceImpl(jsonProviderMock)

        val bonusesList = bonusesRemoteDataSource.getAllBonuses()

        assertTrue(bonusesList.size == 1)
        assertEquals(
            listOf(
                BonusInfo(
                    id = "bonus_1_1",
                    type = "points",
                    value = 200.0,
                    availableDueTo = getDate(),
                    promotionExtra = PromotionExtra(
                        baseColor = "5222FF",
                        tintColor = "FFFFFF",
                        label = "PREMIUM",
                    ),
                )
            ),
            bonusesList,
        )
    }

    @Test
    fun data_source_returns_model_without_promotionExtra_when_JSON_promotion_extra_not_exist_score_1() {
        val jsonProviderMock = mock<JsonProvider> {
            on { allBonusesJson } doReturn """
                [
                    {
                        "id": "bonus_1_2",
                        "type": "cashback",
                        "value": 0.5,
                        "available_due_to": "$DATE_STRING"
                    }
                ]
            """.trimIndent()
        }
        val bonusesRemoteDataSource = BonusesRemoteDataSourceImpl(jsonProviderMock)

        val bonusesList = bonusesRemoteDataSource.getAllBonuses()

        assertTrue(bonusesList.size == 1)
        assertEquals(
            listOf(
                BonusInfo(
                    id = "bonus_1_2",
                    type = "cashback",
                    value = 0.5,
                    availableDueTo = getDate(),
                    promotionExtra = null,
                )
            ),
            bonusesList,
        )
    }

    @Test
    fun data_source_returns_3_models_when_JSON_is_full_and_has_3_objects_score_1() {
        val jsonProviderMock = mock<JsonProvider> {
            on { allBonusesJson } doReturn """
                [
                    {
                        "id": "bonus_1_10",
                        "type": "points",
                        "value": 200,
                        "available_due_to": "$DATE_STRING",
                        "promotion_extra": {
                            "base_color": "5222FF",
                            "tint_color": "FFFFFF",
                            "label": "PREMIUM"
                        }
                    },
                    {
                        "id": "bonus_2",
                        "type": "cashback",
                        "value": 0.3,
                        "available_due_to": "$DATE_STRING"
                    },
                    {
                        "id": "bonus_30",
                        "type": "points",
                        "value": 100.0,
                        "promotion_extra": {
                            "base_color": "000000",
                            "tint_color": "F3F3F3",
                            "label": "PREMIUM"
                        }
                    }
                ]
            """.trimIndent()
        }
        val bonusesRemoteDataSource = BonusesRemoteDataSourceImpl(jsonProviderMock)

        val bonusesList = bonusesRemoteDataSource.getAllBonuses()

        assertTrue(bonusesList.size == 3)
        assertEquals(
            listOf(
                BonusInfo(
                    id = "bonus_1_10",
                    type = "points",
                    value = 200.0,
                    availableDueTo = getDate(),
                    promotionExtra = PromotionExtra(
                        baseColor = "5222FF",
                        tintColor = "FFFFFF",
                        label = "PREMIUM",
                    ),
                ),
                BonusInfo(
                    id = "bonus_2",
                    type = "cashback",
                    value = 0.3,
                    availableDueTo = getDate(),
                    promotionExtra = null,
                ),
                BonusInfo(
                    id = "bonus_30",
                    type = "points",
                    value = 100.0,
                    availableDueTo = null,
                    promotionExtra = PromotionExtra(
                        baseColor = "000000",
                        tintColor = "F3F3F3",
                        label = "PREMIUM",
                    ),
                ),
            ),
            bonusesList,
        )
    }

    private fun getDate(): Date? {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return sdf.parse(DATE_STRING)
    }

    private companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"
        private const val DATE_STRING = "2025-04-23T18:25:43.511Z"
    }
}
