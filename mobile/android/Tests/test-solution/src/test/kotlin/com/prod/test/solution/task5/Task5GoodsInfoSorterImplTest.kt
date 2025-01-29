package com.prod.test.solution.task5

import com.prod.core.api.domain.models.GoodInfo
import com.prod.core.api.domain.models.GoodItemQuantityInfo
import com.prod.core.api.domain.models.GoodProducerInfo
import com.prod.core.api.domain.models.UserInfo
import com.prod.core.api.sorter.GoodsScoreCalculator
import com.prod.solution.impl.sorter.GoodsInfoSorterImpl
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock

class Task5GoodsInfoSorterImplTest {

    @Test
    fun goodsInfoSorterImplTest1Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 0
            on { calculateScore(eq(goodInfo2), any()) } doReturn 2
            on { calculateScore(eq(goodInfo3), any()) } doReturn 2
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo1, goodInfo2, goodInfo3),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo2, goodInfo3, goodInfo1),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest2Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 3
            on { calculateScore(eq(goodInfo2), any()) } doReturn 2
            on { calculateScore(eq(goodInfo3), any()) } doReturn 1
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo1, goodInfo2, goodInfo3),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo1, goodInfo2, goodInfo3),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest3Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 0
            on { calculateScore(eq(goodInfo2), any()) } doReturn 0
            on { calculateScore(eq(goodInfo3), any()) } doReturn 0
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo1, goodInfo2, goodInfo3),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo1, goodInfo2, goodInfo3),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest4Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 1
            on { calculateScore(eq(goodInfo2), any()) } doReturn 2
            on { calculateScore(eq(goodInfo3), any()) } doReturn 3
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo1, goodInfo2, goodInfo3),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo3, goodInfo2, goodInfo1),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest5Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 1
            on { calculateScore(eq(goodInfo2), any()) } doReturn 2
            on { calculateScore(eq(goodInfo3), any()) } doReturn 3
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo1, goodInfo3, goodInfo2),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo3, goodInfo2, goodInfo1),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest6Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 1
            on { calculateScore(eq(goodInfo2), any()) } doReturn 2
            on { calculateScore(eq(goodInfo3), any()) } doReturn 3
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo3, goodInfo2, goodInfo1),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo3, goodInfo2, goodInfo1),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest7Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 1
            on { calculateScore(eq(goodInfo2), any()) } doReturn 2
            on { calculateScore(eq(goodInfo3), any()) } doReturn 3
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo3, goodInfo1, goodInfo2),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo3, goodInfo2, goodInfo1),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest8Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 1
            on { calculateScore(eq(goodInfo2), any()) } doReturn 2
            on { calculateScore(eq(goodInfo3), any()) } doReturn 3
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo2, goodInfo1, goodInfo3),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo3, goodInfo2, goodInfo1),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest9Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 1
            on { calculateScore(eq(goodInfo2), any()) } doReturn 2
            on { calculateScore(eq(goodInfo3), any()) } doReturn 3
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo2, goodInfo3, goodInfo1),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo3, goodInfo2, goodInfo1),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest10Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 3
            on { calculateScore(eq(goodInfo2), any()) } doReturn 2
            on { calculateScore(eq(goodInfo3), any()) } doReturn 1
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo1, goodInfo2, goodInfo3),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo1, goodInfo2, goodInfo3),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest11Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 3
            on { calculateScore(eq(goodInfo2), any()) } doReturn 1
            on { calculateScore(eq(goodInfo3), any()) } doReturn 2
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo1, goodInfo2, goodInfo3),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo1, goodInfo3, goodInfo2),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest12Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 2
            on { calculateScore(eq(goodInfo2), any()) } doReturn 3
            on { calculateScore(eq(goodInfo3), any()) } doReturn 1
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo1, goodInfo2, goodInfo3),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo2, goodInfo1, goodInfo3),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest13Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 1
            on { calculateScore(eq(goodInfo2), any()) } doReturn 3
            on { calculateScore(eq(goodInfo3), any()) } doReturn 2
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo1, goodInfo2, goodInfo3),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo2, goodInfo3, goodInfo1),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest14Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 2
            on { calculateScore(eq(goodInfo2), any()) } doReturn 1
            on { calculateScore(eq(goodInfo3), any()) } doReturn 3
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo1, goodInfo2, goodInfo3),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo3, goodInfo1, goodInfo2),
            actual,
        )
    }

    @Test
    fun goodsInfoSorterImplTest15Score1() {
        val goodsScoreCalculator = mock<GoodsScoreCalculator> {
            on { calculateScore(eq(goodInfo1), any()) } doReturn 1
            on { calculateScore(eq(goodInfo2), any()) } doReturn 2
            on { calculateScore(eq(goodInfo3), any()) } doReturn 3
        }
        val sorter = GoodsInfoSorterImpl(goodsScoreCalculator)

        val actual = sorter.sortGoodsInfo(
            goods = listOf(goodInfo1, goodInfo2, goodInfo3),
            userInfo = userInfo,
        )

        assertEquals(
            listOf(goodInfo3, goodInfo2, goodInfo1),
            actual,
        )
    }

    private companion object {
        val goodInfo1 = GoodInfo(
            id = "good_13647283-dao_sdasfdsjkg",
            imageId = "goods-1-img_samdkfj",
            name = "Хлеб с отрубями Хрустяшка, а что хрустит",
            producer = GoodProducerInfo(
                id = "prodai-1212-adff",
                name = "АО Московский хрящекомбинат333",
            ),
            isNew = false,
            goodItemQuantityInfo = GoodItemQuantityInfo(
                type = "gramm",
                value = 200,
            ),
            cost = 130,
            popularity = 100,
            category = "HLEB",
            bonusIds = listOf("ada", "dad"),
            rating = 15.2,
        )
        val goodInfo2 = GoodInfo(
            id = "good_13649783-wpo",
            imageId = "goods-4-img",
            name = "Манго сочное. Египет",
            producer = GoodProducerInfo(
                id = "prodai-31323",
                name = "Египетские сельхоз проля Дары Клеопатры",
            ),
            isNew = false,
            goodItemQuantityInfo = GoodItemQuantityInfo(
                type = "kilo",
                value = 3,
            ),
            cost = 250,
            popularity = 35,
            category = "FRUITS",
            bonusIds = listOf("bonus_13132", "bonus_4243"),
            rating = 4.5,
        )
        val goodInfo3 = GoodInfo(
            id = "5",
            imageId = "goods-placeholder",
            name = "Хлеб Т-банковский красный умный прекрасный",
            producer = GoodProducerInfo(
                id = "1",
                name = "Простоквашино"
            ),
            isNew = false,
            goodItemQuantityInfo = GoodItemQuantityInfo(
                type = "kilo",
                value = 1,
            ),
            cost = 1259,
            popularity = 10,
            category = ""
        )
        val userInfo = UserInfo(
            lastGoodsCategories = listOf(),
            availableBonuses = listOf("bon1", "bon2", "bon3", "bon4"),
            favorites = listOf("6"),
            activityLevel = 10
        )
    }
}
