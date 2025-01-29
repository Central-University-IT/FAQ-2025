package com.prod.test.solution

import com.prod.core.api.domain.models.BonusInfo
import com.prod.core.api.domain.models.GoodCartInfo
import com.prod.core.api.domain.models.GoodInfo
import com.prod.core.api.domain.models.GoodItemQuantityInfo
import com.prod.core.api.domain.models.GoodProducerInfo
import com.prod.core.api.domain.models.UserInfo
import com.prod.core.api.domain.repositories.CartRepository
import com.prod.core.api.domain.repositories.UserRepository
import com.prod.core.api.domain.usecases.GetBonusInfoFromGoodInfoUseCase
import com.prod.solution.impl.domain.repositories.CartManagerImpl
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class Task7Test {

    @Test
    fun should_return_sum_of_costs_score_6() {
        val cartRepository = mock<CartRepository> {
            on { getCart() } doReturn listOf(first to 1, second to 2)
        }
        val cartManagerImpl = CartManagerImpl(cartRepository, mock {}, mock {})

        assert(cartManagerImpl.calculateCosts() == 300)
    }

    @Test
    fun should_return_all_weight_of_goods_in_kilograms_score_6() {
        val cartRepository = mock<CartRepository> {
            on { getCart() } doReturn listOf(first to 1, second to 2, third to 2)
        }
        val cartManagerImpl = CartManagerImpl(cartRepository, mock { }, mock {})

        assert(cartManagerImpl.calculateAllWeight() == 3.5)
    }

    @Test
    fun should_return_data_for_presentation_on_the_screen_for_all_goods_score_6() {
        val cartRepository = mock<CartRepository> {
            on { getCart() } doReturn listOf(first to 1, second to 2, third to 2, fourth to 3)
        }
        val cartManagerImpl =
            CartManagerImpl(cartRepository, mock {}, mock {})

        assert(
            cartManagerImpl.calculateGoodsData() == listOf(
                GoodCartInfo(
                    goodInfo = first,
                    quantityValue = 500,
                    totalCost = 100,
                    countInCart = 1
                ),
                GoodCartInfo(
                    goodInfo = second,
                    quantityValue = 1000,
                    totalCost = 200,
                    countInCart = 2
                ),
                GoodCartInfo(
                    goodInfo = third,
                    quantityValue = 2,
                    totalCost = 600,
                    countInCart = 2
                ),
                GoodCartInfo(
                    goodInfo = fourth,
                    quantityValue = 6,
                    totalCost = 900,
                    countInCart = 3
                ),
            )
        )

    }

    @Test
    fun should_return_sum_of_cashback_without_favorite_goods_score_6() {
        val cartRepository = mock<CartRepository> {
            on { getCart() } doReturn listOf(first to 1, second to 2, third to 2)
        }
        val userRepository = mock<UserRepository> {
            on { getUserInfo() } doReturn userWithoutFavoriteGoods
        }
        val getBonusInfoFromGoodInfoUseCase = mock<GetBonusInfoFromGoodInfoUseCase> {
            on { getBonusInfo(first) } doReturn bonus1
            on { getBonusInfo(second) } doReturn bonus2
            on { getBonusInfo(third) } doReturn bonus3
        }
        val cartManagerImpl =
            CartManagerImpl(cartRepository, getBonusInfoFromGoodInfoUseCase, userRepository)

        // 10 за первый товар и по 60 за два третих товара, итого 130
        assert(cartManagerImpl.calculateCashback() == 130)
    }

    @Test
    fun should_return_sum_of_cashback_with_favorite_goods_score_6() {
        val cartRepository = mock<CartRepository> {
            on { getCart() } doReturn listOf(first to 1, second to 2, third to 2)
        }
        val userRepository = mock<UserRepository> {
            on { getUserInfo() } doReturn userWithFavoriteGoodsCashback
        }
        val getBonusInfoFromGoodInfoUseCase = mock<GetBonusInfoFromGoodInfoUseCase> {
            on { getBonusInfo(first) } doReturn bonus1
            on { getBonusInfo(second) } doReturn bonus2
            on { getBonusInfo(third) } doReturn bonus3
        }
        val cartManagerImpl =
            CartManagerImpl(cartRepository, getBonusInfoFromGoodInfoUseCase, userRepository)

        // 100 * 0.1 = 10 за первый товар и по 300 * 0.2 * 1.2 = 72 за два третих товара, итого 154
        assert(cartManagerImpl.calculateCashback() == 154)
    }

    @Test
    fun should_return_sum_of_cashback_with_favorite_goods_and_without_bonus_and_small_activity_score_6() {
        val cartRepository = mock<CartRepository> {
            on { getCart() } doReturn listOf(first to 1, second to 2, third to 2, sixth to 3)
        }
        val userRepository = mock<UserRepository> {
            on { getUserInfo() } doReturn userWithFavoriteGoodsSmallActivity
        }
        val getBonusInfoFromGoodInfoUseCase = mock<GetBonusInfoFromGoodInfoUseCase> {
            on { getBonusInfo(first) } doReturn bonus1
            on { getBonusInfo(second) } doReturn bonus2
            on { getBonusInfo(third) } doReturn bonus3
        }
        val cartManagerImpl =
            CartManagerImpl(cartRepository, getBonusInfoFromGoodInfoUseCase, userRepository)


        // 100 * 0.1 = 10 за первый товар и по 300 * 0.2 * 2 = 120 за два третих товара,
        // 500 * 0 * 3 = 0 за три шестых товара, итого 130
        assert(cartManagerImpl.calculateCashback() == 130)
    }

    @Test
    fun should_return_sum_of_cashback_with_favorite_goods_and_without_bonus_and_medium_activity_score_6() {
        val cartRepository = mock<CartRepository> {
            on { getCart() } doReturn listOf(first to 1, second to 2, third to 2, sixth to 3)
        }
        val userRepository = mock<UserRepository> {
            on { getUserInfo() } doReturn userWithFavoriteGoodsMediumActivity
        }
        val getBonusInfoFromGoodInfoUseCase = mock<GetBonusInfoFromGoodInfoUseCase> {
            on { getBonusInfo(first) } doReturn bonus1
            on { getBonusInfo(second) } doReturn bonus2
            on { getBonusInfo(third) } doReturn bonus3
        }
        val cartManagerImpl =
            CartManagerImpl(cartRepository, getBonusInfoFromGoodInfoUseCase, userRepository)


        // 100 * 0.1 = 10 за первый товар и по 300 * 0.2 * 2 = 120 за два третих товара,
        // 500 * 0.02 * 3 = 30 итого 160
        assert(cartManagerImpl.calculateCashback() == 160)
    }

    @Test
    fun should_return_sum_of_cashback_with_favorite_goods_and_without_bonus_and_large_activity_score_6() {
        val cartRepository = mock<CartRepository> {
            on { getCart() } doReturn listOf(first to 1, second to 2, third to 2, sixth to 3)
        }
        val userRepository = mock<UserRepository> {
            on { getUserInfo() } doReturn userWithFavoriteGoodsLargeActivity
        }
        val getBonusInfoFromGoodInfoUseCase = mock<GetBonusInfoFromGoodInfoUseCase> {
            on { getBonusInfo(first) } doReturn bonus1
            on { getBonusInfo(second) } doReturn bonus2
            on { getBonusInfo(third) } doReturn bonus3
        }
        val cartManagerImpl =
            CartManagerImpl(cartRepository, getBonusInfoFromGoodInfoUseCase, userRepository)


        // 100 * 0.1 = 10 за первый товар и по 300 * 0.2 * 2= 120 за два третих товара,
        // 500 * 0.03 * 3 = 45 итого 175
        assert(cartManagerImpl.calculateCashback() == 175)
    }

    @Test
    fun should_return_sum_of_cashback_with_favorite_goods_and_without_bonus_and_extra_large_activity_score_6() {
        val cartRepository = mock<CartRepository> {
            on { getCart() } doReturn listOf(first to 1, second to 2, third to 2, sixth to 3)
        }
        val userRepository = mock<UserRepository> {
            on { getUserInfo() } doReturn userWithFavoriteGoodsExtraLargeActivity
        }
        val getBonusInfoFromGoodInfoUseCase = mock<GetBonusInfoFromGoodInfoUseCase> {
            on { getBonusInfo(first) } doReturn bonus1
            on { getBonusInfo(second) } doReturn bonus2
            on { getBonusInfo(third) } doReturn bonus3
        }
        val cartManagerImpl =
            CartManagerImpl(cartRepository, getBonusInfoFromGoodInfoUseCase, userRepository)


        // 100 * 0.1 = 10 за первый товар и по 300 * 0.2 * 2= 120 за два третих товара,
        // 500 * 0.05 * 3 = 75 итого 205
        assert(cartManagerImpl.calculateCashback() == 205)
    }

    @Test
    fun should_return_sum_of_bonus_points_without_favorite_goods_score_6() {
        val cartRepository = mock<CartRepository> {
            on { getCart() } doReturn listOf(first to 1, second to 2, third to 2, fourth to 2)
        }
        val userRepository = mock<UserRepository> {
            on { getUserInfo() } doReturn userWithoutFavoriteGoods
        }
        val getBonusInfoFromGoodInfoUseCase = mock<GetBonusInfoFromGoodInfoUseCase> {
            on { getBonusInfo(first) } doReturn bonus1
            on { getBonusInfo(second) } doReturn bonus2
            on { getBonusInfo(third) } doReturn bonus3
            on { getBonusInfo(fourth) } doReturn bonus4
        }
        val cartManagerImpl =
            CartManagerImpl(cartRepository, getBonusInfoFromGoodInfoUseCase, userRepository)

        // 500 * 2 == 1000 за два вторых товара и по 1000 за два четвертых товара 1000 * 2 = 2000, итого
        // 3000
        assert(cartManagerImpl.calculateBonuses() == 3000)
    }

    @Test
    fun should_return_sum_of_bonuses_with_favorite_goods_score_6() {
        val cartRepository = mock<CartRepository> {
            on { getCart() } doReturn listOf(
                first to 1,
                second to 2,
                third to 2,
                fourth to 2,
                fifth to 1
            )
        }
        val userRepository = mock<UserRepository> {
            on { getUserInfo() } doReturn userWithFavoriteGoodsPoints
        }
        val getBonusInfoFromGoodInfoUseCase = mock<GetBonusInfoFromGoodInfoUseCase> {
            on { getBonusInfo(first) } doReturn bonus1
            on { getBonusInfo(second) } doReturn bonus2
            on { getBonusInfo(third) } doReturn bonus3
            on { getBonusInfo(fourth) } doReturn bonus4
            on { getBonusInfo(fifth) } doReturn bonus4
        }
        val cartManagerImpl =
            CartManagerImpl(cartRepository, getBonusInfoFromGoodInfoUseCase, userRepository)

        // 500 * 2 * 1.2 = 1200 за два вторых товара и по 1000 * 2 * 1.2 = 2400 за два четвертых товара,
        // и 1000 бонусов за пятый товар, итого 4600
        assert(cartManagerImpl.calculateBonuses() == 4600)
    }

    companion object {
        val bonus1 = BonusInfo(
            id = "bon1",
            type = "cashback",
            value = 0.1
        )

        val bonus2 = BonusInfo(
            id = "bon2",
            type = "points",
            value = 500.0
        )

        val bonus3 = BonusInfo(
            id = "bon3",
            type = "cashback",
            value = 0.2
        )

        val bonus4 = BonusInfo(
            id = "bon4",
            type = "points",
            value = 1000.0
        )

        val first = GoodInfo(
            id = "1",
            imageId = "null",
            name = "name1",
            producer = GoodProducerInfo(
                id = "1",
                name = "name"
            ),
            isNew = false,
            goodItemQuantityInfo = GoodItemQuantityInfo(
                type = "gramm",
                value = 500,
            ),
            cost = 100,
            popularity = 10,
            category = "cat",
            bonusIds = listOf("bon1")
        )

        val second = GoodInfo(
            id = "2",
            imageId = "null",
            name = "name2",
            producer = GoodProducerInfo(
                id = "1",
                name = "name"
            ),
            isNew = false,
            goodItemQuantityInfo = GoodItemQuantityInfo(
                type = "gramm",
                value = 500,
            ),
            cost = 100,
            popularity = 10,
            category = "cat",
            bonusIds = listOf("bon2")
        )

        val third = GoodInfo(
            id = "3",
            imageId = "null",
            name = "name2",
            producer = GoodProducerInfo(
                id = "1",
                name = "name"
            ),
            isNew = false,
            goodItemQuantityInfo = GoodItemQuantityInfo(
                type = "kilo",
                value = 1,
            ),
            cost = 300,
            popularity = 10,
            category = "cat",
            bonusIds = listOf("bon3")
        )

        val fourth = GoodInfo(
            id = "4",
            imageId = "null",
            name = "name2",
            producer = GoodProducerInfo(
                id = "1",
                name = "name"
            ),
            isNew = false,
            goodItemQuantityInfo = GoodItemQuantityInfo(
                type = "kilo",
                value = 2,
            ),
            cost = 300,
            popularity = 10,
            category = "cat",
            bonusIds = listOf("bon4")
        )

        val fifth = GoodInfo(
            id = "5",
            imageId = "null",
            name = "name2",
            producer = GoodProducerInfo(
                id = "1",
                name = "name"
            ),
            isNew = false,
            goodItemQuantityInfo = GoodItemQuantityInfo(
                type = "gramm",
                value = 500,
            ),
            cost = 300,
            popularity = 10,
            category = "cat",
            bonusIds = listOf("bon4")
        )

        val sixth = GoodInfo(
            id = "6",
            imageId = "null",
            name = "name2",
            producer = GoodProducerInfo(
                id = "1",
                name = "name"
            ),
            isNew = false,
            goodItemQuantityInfo = GoodItemQuantityInfo(
                type = "gramm",
                value = 500,
            ),
            cost = 500,
            popularity = 10,
            category = "cat",
        )

        val userWithoutFavoriteGoods = UserInfo(
            lastGoodsCategories = listOf(),
            availableBonuses = listOf("bon1", "bon2", "bon3", "bon4"),
            favorites = listOf(),
            activityLevel = 0
        )

        val userWithFavoriteGoodsPoints = UserInfo(
            lastGoodsCategories = listOf(),
            availableBonuses = listOf("bon1", "bon2", "bon3", "bon4"),
            favorites = listOf("2", "4"),
            activityLevel = 0
        )

        val userWithFavoriteGoodsCashback = UserInfo(
            lastGoodsCategories = listOf(),
            availableBonuses = listOf("bon1", "bon2", "bon3", "bon4"),
            favorites = listOf("3"),
            activityLevel = 0
        )

        val userWithFavoriteGoodsSmallActivity = UserInfo(
            lastGoodsCategories = listOf(),
            availableBonuses = listOf("bon1", "bon2", "bon3", "bon4"),
            favorites = listOf("6"),
            activityLevel = 10
        )

        val userWithFavoriteGoodsMediumActivity = UserInfo(
            lastGoodsCategories = listOf(),
            availableBonuses = listOf("bon1", "bon2", "bon3", "bon4"),
            favorites = listOf("6"),
            activityLevel = 45
        )

        val userWithFavoriteGoodsLargeActivity = UserInfo(
            lastGoodsCategories = listOf(),
            availableBonuses = listOf("bon1", "bon2", "bon3", "bon4"),
            favorites = listOf("6"),
            activityLevel = 65
        )

        val userWithFavoriteGoodsExtraLargeActivity = UserInfo(
            lastGoodsCategories = listOf(),
            availableBonuses = listOf("bon1", "bon2", "bon3", "bon4"),
            favorites = listOf("6"),
            activityLevel = 95
        )
    }
}
