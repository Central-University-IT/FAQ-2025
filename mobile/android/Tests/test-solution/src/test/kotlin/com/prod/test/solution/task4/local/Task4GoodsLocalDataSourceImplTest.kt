package com.prod.test.solution.task4.local

import com.prod.core.api.domain.models.GoodInfo
import com.prod.core.api.domain.models.GoodItemQuantityInfo
import com.prod.core.api.domain.models.GoodProducerInfo
import com.prod.solution.impl.data.sources.local.GoodsLocalDataSourceImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class Task4GoodsLocalDataSourceImplTest {

    @Test
    fun goodsLocalDataSourceImpl_returns_empty_list_when_getCachedGoods_if_nothing_cached_score_1() {
        val goodsLocalDataSource = GoodsLocalDataSourceImpl()

        val actual = goodsLocalDataSource.getCachedGoods()

        assertTrue(actual.isEmpty())
    }

    @Test
    fun goodsLocalDataSourceImpl_returns_list_with_1_cached_item_when_cacheGoods_and_getCachedGoods_called_score_1() {
        val expected = listOf(
            GoodInfo(
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
            ),
        )
        val goodsLocalDataSource = GoodsLocalDataSourceImpl()

        goodsLocalDataSource.cacheGoods(expected)
        val actual = goodsLocalDataSource.getCachedGoods()

        assertEquals(expected, actual)
    }

    @Test
    fun goodsLocalDataSourceImpl_returns_list_with_2_cached_items_when_cacheGoods_and_getCachedGoods_called_score_1() {
        val expected = listOf(
            GoodInfo(
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
            ),
            GoodInfo(
                id = "good_13647283-dao-asfdd",
                imageId = "goods-1-img-afds",
                name = "Хлеб с отрубями Хрустяшка",
                producer = GoodProducerInfo(
                    id = "prodai-31323-asfd",
                    name = "АО Московский хрящекомбинат",
                ),
                isNew = true,
                goodItemQuantityInfo = GoodItemQuantityInfo(
                    type = "gramm",
                    value = 2000,
                ),
                cost = 130,
                popularity = 100,
                category = "HLEB",
                bonusIds = listOf("ada"),
                rating = null,
            )
        )
        val goodsLocalDataSource = GoodsLocalDataSourceImpl()

        goodsLocalDataSource.cacheGoods(expected)
        val actual = goodsLocalDataSource.getCachedGoods()

        assertEquals(expected, actual)
    }
}
