package com.prod.test.solution

import com.prod.core.api.domain.models.GoodInfo
import com.prod.core.api.domain.models.GoodItemQuantityInfo
import com.prod.core.api.domain.models.GoodProducerInfo
import com.prod.solution.impl.domain.repositories.CartRepositoryImpl
import org.junit.Test


class Task8Test {

    private val cartRepositoryImpl = CartRepositoryImpl()

    @Test
    fun cartInitialStateTestScore5() {
        assert(cartRepositoryImpl.getCart().isEmpty())
    }

    @Test
    fun should_add_one_good_to_cart_score_5() {
        cartRepositoryImpl.increaseGoodQuantity(first)
        assert(cartRepositoryImpl.getCart() == listOf(first to 1))
    }

    @Test
    fun should_add_two_different_goods_to_cart_score_5() {
        cartRepositoryImpl.increaseGoodQuantity(first)
        cartRepositoryImpl.increaseGoodQuantity(second)
        assert(cartRepositoryImpl.getCart() == listOf(first to 1, second to 1))
    }

    @Test
    fun should_add_two_different_goods_each_score_5() {
        cartRepositoryImpl.increaseGoodQuantity(first)
        cartRepositoryImpl.increaseGoodQuantity(second)
        cartRepositoryImpl.increaseGoodQuantity(first)
        cartRepositoryImpl.increaseGoodQuantity(second)
        assert(cartRepositoryImpl.getCart() == listOf(first to 2, second to 2))
    }

    @Test
    fun should_add_two_different_goods_in_input_s_order_score_5() {
        cartRepositoryImpl.increaseGoodQuantity(second)
        cartRepositoryImpl.increaseGoodQuantity(first)
        cartRepositoryImpl.increaseGoodQuantity(second)
        cartRepositoryImpl.increaseGoodQuantity(first)
        assert(cartRepositoryImpl.getCart() == listOf(second to 2, first to 2))
    }

    @Test
    fun should_delete_one_good_from_not_empty_cart_score_5() {
        cartRepositoryImpl.increaseGoodQuantity(first)
        cartRepositoryImpl.increaseGoodQuantity(second)
        cartRepositoryImpl.increaseGoodQuantity(first)
        cartRepositoryImpl.increaseGoodQuantity(second)
        cartRepositoryImpl.decreaseGoodQuantity(first)
        assert(cartRepositoryImpl.getCart() == listOf(first to 1, second to 2))
    }

    @Test
    fun should_delete_good_from_not_empty_cart_manually_score_5() {
        cartRepositoryImpl.increaseGoodQuantity(first)
        cartRepositoryImpl.increaseGoodQuantity(second)
        cartRepositoryImpl.increaseGoodQuantity(first)
        cartRepositoryImpl.increaseGoodQuantity(second)
        cartRepositoryImpl.decreaseGoodQuantity(first)
        cartRepositoryImpl.decreaseGoodQuantity(first)
        assert(cartRepositoryImpl.getCart() == listOf(second to 2))
    }

    @Test
    fun should_delete_good_from_not_empty_cart_score_6() {
        cartRepositoryImpl.increaseGoodQuantity(first)
        cartRepositoryImpl.increaseGoodQuantity(second)
        cartRepositoryImpl.increaseGoodQuantity(first)
        cartRepositoryImpl.increaseGoodQuantity(second)
        cartRepositoryImpl.deleteAllQuantitiesFromCart(first)
        assert(cartRepositoryImpl.getCart() == listOf(second to 2))
    }

    companion object {
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
            category = "cat"
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
            category = "cat"
        )
    }
}
