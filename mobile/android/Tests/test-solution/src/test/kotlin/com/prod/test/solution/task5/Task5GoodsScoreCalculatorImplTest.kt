package com.prod.test.solution.task5

import com.prod.core.api.domain.models.GoodInfo
import com.prod.core.api.domain.models.GoodItemQuantityInfo
import com.prod.core.api.domain.models.GoodProducerInfo
import com.prod.core.api.domain.models.UserInfo
import com.prod.solution.impl.sorter.GoodsScoreCalculatorImpl
import junit.framework.TestCase.assertEquals
import org.junit.Test

class Task5GoodsScoreCalculatorImplTest {

    @Test
    fun goodsScoreCalculatorImplTestPopularity1Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                popularity = 75,
                rating = 4.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(0, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestPopularity2Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                popularity = 49,
                rating = 4.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(-1, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestPopularity3Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                popularity = 50,
                rating = 4.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(0, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestPopularity4Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                popularity = 51,
                rating = 4.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(0, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestPopularity5Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                popularity = 99,
                rating = 4.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(0, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestPopularity6Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                popularity = 100,
                rating = 4.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(0, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestPopularity7Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                popularity = 101,
                rating = 4.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(3, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestPopularity8Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                popularity = 150,
                rating = 4.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(3, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestCategory1Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat1",
                popularity = 75,
                rating = 4.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf("cat1", "cat2", "cat3"),
            ),
        )

        assertEquals(2, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestCategory2Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat2",
                popularity = 75,
                rating = 4.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf("cat1", "cat2", "cat3"),
            ),
        )

        assertEquals(1, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestCategory3Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat3",
                popularity = 75,
                rating = 4.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf("cat1", "cat2", "cat3"),
            ),
        )

        assertEquals(0, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestCategory4Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat4",
                popularity = 75,
                rating = 4.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf("cat1", "cat2", "cat3"),
            ),
        )

        assertEquals(0, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestRating1Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat4",
                popularity = 75,
                rating = 4.49,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(0, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestRating2Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat4",
                popularity = 75,
                rating = 3.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(0, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestRating3Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat4",
                popularity = 75,
                rating = 2.99,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(-1, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestRating4Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat4",
                popularity = 75,
                rating = 2.5,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(-1, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestRating5Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat4",
                popularity = 75,
                rating = 2.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(-1, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestRating6Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat4",
                popularity = 75,
                rating = 1.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(-1, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestRating7Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat4",
                popularity = 75,
                rating = 0.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(-1, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestRating8Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat4",
                popularity = 75,
                rating = 4.501,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(2, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestRating9Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat4",
                popularity = 75,
                rating = 4.499,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(0, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestRating10Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat4",
                popularity = 75,
                rating = 4.75,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(2, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestRating11Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat4",
                popularity = 75,
                rating = 5.0,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(2, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestFavourite1Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                category = "cat4",
                popularity = 75,
                rating = null,
            ),
            userInfo = userInfo.copy(
                favorites = listOf(),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(0, score)
    }

    @Test
    fun goodsScoreCalculatorImplTestFavourite2Score1() {
        val calculator = GoodsScoreCalculatorImpl()

        val score = calculator.calculateScore(
            goods = goodInfo1.copy(
                id = "item5",
                category = "cat4",
                popularity = 75,
                rating = null,
            ),
            userInfo = userInfo.copy(
                favorites = listOf("item5"),
                lastGoodsCategories = listOf(),
            ),
        )

        assertEquals(2, score)
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
            popularity = 75,
            category = "HLEB",
            bonusIds = listOf("ada", "dad"),
            rating = 4.0,
        )
        val userInfo = UserInfo(
            lastGoodsCategories = listOf(),
            availableBonuses = listOf(),
            favorites = listOf(),
            activityLevel = 0
        )
    }
}
