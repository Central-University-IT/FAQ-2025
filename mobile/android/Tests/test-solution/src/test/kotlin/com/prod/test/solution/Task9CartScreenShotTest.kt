package com.prod.test.solution

import com.prod.core.api.domain.models.GoodCartInfo
import com.prod.core.api.domain.models.GoodInfo
import com.prod.core.api.domain.models.GoodItemQuantityInfo
import com.prod.core.api.domain.models.GoodProducerInfo
import com.prod.core.api.ui.cart.CartScreenState
import com.prod.solution.impl.ui.cart.CartScreenView
import org.junit.Test

class Task9CartScreenShotTest : BaseScreenShotTest() {

    @Test
    fun regular_with_bonuses_and_cashback_score_23() {
        val state = CartScreenState(
            totalCosts = 6000,
            totalBonuses = 200,
            totalCashback = 1000,
            totalWeight = 2.4,
            goodsData = listOf(
                firstGood, secondGood, thirdGood, fourthGood, fifthGood
            )
        )
        val view = CartScreenView(paparazzi.context).apply {
            updateState(state, {}, {})
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun regular_with_only_cashback_score_21() {
        val state = CartScreenState(
            totalCosts = 6000,
            totalBonuses = 0,
            totalCashback = 1000,
            totalWeight = 2.4,
            goodsData = listOf(
                firstGood, secondGood, thirdGood, fourthGood, fifthGood
            )
        )
        val view = CartScreenView(paparazzi.context).apply {
            updateState(state, {}, {})
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun regular_with_only_bonuses_score_21() {
        val state = CartScreenState(
            totalCosts = 6000,
            totalBonuses = 200,
            totalCashback = 0,
            totalWeight = 2222.4,
            goodsData = listOf(
                firstGood, secondGood, thirdGood, fourthGood, fifthGood
            )
        )
        val view = CartScreenView(paparazzi.context).apply {
            updateState(state, {}, {})
        }

        paparazzi.snapshot(view)
    }

    @Test
    fun regular_without_anything_score_21() {
        val state = CartScreenState(
            totalCosts = 600000,
            totalBonuses = 0,
            totalCashback = 0,
            totalWeight = 2.4,
            goodsData = listOf(
                firstGood, secondGood, thirdGood, fourthGood, fifthGood
            )
        )
        val view = CartScreenView(paparazzi.context).apply {
            updateState(state, {}, {})
        }

        paparazzi.snapshot(view)
    }

    companion object {
        val firstGood = GoodCartInfo(
            goodInfo = GoodInfo(
                id = "1",
                imageId = "goods-1-img",
                name = "Хлеб 'Российский'",
                producer = GoodProducerInfo(
                    id = "1",
                    name = "Простоквашино"
                ),
                isNew = false,
                goodItemQuantityInfo = GoodItemQuantityInfo(
                    type = "gramm",
                    value = 500,
                ),
                cost = 500,
                popularity = 10,
                category = ""
            ),
            quantityValue = 1000,
            totalCost = 1000,
            countInCart = 2,
        )

        val secondGood = GoodCartInfo(
            goodInfo = GoodInfo(
                id = "2",
                imageId = "goods-2-img",
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
            ),
            quantityValue = 2,
            totalCost = 26903,
            countInCart = 2,
        )

        val thirdGood = GoodCartInfo(
            goodInfo = GoodInfo(
                id = "3",
                imageId = "goods-3-img",
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
            ),
            quantityValue = 2,
            totalCost = 26903,
            countInCart = 2,
        )

        val fourthGood = GoodCartInfo(
            goodInfo = GoodInfo(
                id = "4",
                imageId = "goods-4-img",
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
            ),
            quantityValue = 2,
            totalCost = 26903,
            countInCart = 2,
        )

        val fifthGood = GoodCartInfo(
            goodInfo = GoodInfo(
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
            ),
            quantityValue = 2,
            totalCost = 26903,
            countInCart = 2,
        )
    }
}
