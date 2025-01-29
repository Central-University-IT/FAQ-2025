package com.prod.test.solution.task4.local

import com.prod.core.api.domain.models.BonusInfo
import com.prod.core.api.domain.models.PromotionExtra
import com.prod.solution.impl.data.sources.local.BonusesLocalDataSourceImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.util.Date

class Task4BonusesLocalDataSourceImplTest {

    @Test
    fun bonusesLocalDataSourceImpl_returns_empty_list_when_getCachedBonuses_if_nothing_cached_score_1() {
        val bonusesLocalDataSource = BonusesLocalDataSourceImpl()

        val actual = bonusesLocalDataSource.getCachedBonuses()

        assertTrue(actual.isEmpty())
    }

    @Test
    fun bonusesLocalDataSourceImpl_returns_list_with_1_cached_item_when_cacheBonuses_and_getCachedBonuses_called_score_1() {
        val expected = listOf(
            BonusInfo(
                id = "id",
                type = "type",
                value = 317.73,
                availableDueTo = Date(),
                promotionExtra = null,
            ),
        )
        val bonusesLocalDataSource = BonusesLocalDataSourceImpl()

        bonusesLocalDataSource.cacheBonuses(expected)
        val actual = bonusesLocalDataSource.getCachedBonuses()

        assertEquals(expected, actual)
    }

    @Test
    fun bonusesLocalDataSourceImpl_returns_list_with_2_cached_items_when_cacheBonuses_and_getCachedBonuses_called_score_1() {
        val expected = listOf(
            BonusInfo(
                id = "id",
                type = "type",
                value = 317.73,
                availableDueTo = Date(),
                promotionExtra = null,
            ),
            BonusInfo(
                id = "id2",
                type = "type2",
                value = 717.33,
                availableDueTo = null,
                promotionExtra = PromotionExtra(
                    baseColor = "1",
                    tintColor = "2",
                    label = "1",
                ),
            ),
        )
        val bonusesLocalDataSource = BonusesLocalDataSourceImpl()

        bonusesLocalDataSource.cacheBonuses(expected)
        val actual = bonusesLocalDataSource.getCachedBonuses()

        assertEquals(expected, actual)
    }
}
