package com.prod.test.solution.task4.repository

import com.prod.core.api.data.sources.local.GoodsLocalDataSource
import com.prod.core.api.data.sources.remote.GoodsRemoteDataSource
import com.prod.core.api.domain.models.GoodInfo
import com.prod.core.api.domain.models.GoodItemQuantityInfo
import com.prod.core.api.domain.models.GoodProducerInfo
import com.prod.solution.impl.domain.repositories.GoodsRepositoryImpl
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions

class Task4GoodsRepositoryImplTest {

    @Test
    fun goodsRepositoryImpl_returns_cache_when_getAllGoods_if_cache_has_list_with_1_item_score_2() {
        val localDataSource = mock<GoodsLocalDataSource> {
            on { getCachedGoods() } doReturn listOf(goodInfo1)
        }
        val remoteDataSource = mock<GoodsRemoteDataSource>()
        val repository = GoodsRepositoryImpl(
            goodsLocalDataSource = localDataSource,
            goodsRemoteDataSource = remoteDataSource,
        )

        val actual = repository.getAllGoods()

        assertEquals(listOf(goodInfo1), actual)
        verify(localDataSource, times(1)).getCachedGoods()
        verifyNoInteractions(remoteDataSource)
    }

    @Test
    fun goodsRepositoryImpl_returns_cache_when_getAllGoods_if_cache_has_list_with_2_item_score_2() {
        val localDataSource = mock<GoodsLocalDataSource> {
            on { getCachedGoods() } doReturn listOf(goodInfo1, goodInfo2)
        }
        val remoteDataSource = mock<GoodsRemoteDataSource>()
        val repository = GoodsRepositoryImpl(
            goodsLocalDataSource = localDataSource,
            goodsRemoteDataSource = remoteDataSource,
        )

        val actual = repository.getAllGoods()

        assertEquals(listOf(goodInfo1, goodInfo2), actual)
        verify(localDataSource, times(1)).getCachedGoods()
        verifyNoInteractions(remoteDataSource)
    }

    @Test
    fun goodsRepositoryImpl_returns_remote_data__and_cache_when_getAllGoods_if_cache_is_empty_score_2() {
        val localDataSource = mock<GoodsLocalDataSource> {
            on { getCachedGoods() } doReturn listOf()
        }
        val remoteDataSource = mock<GoodsRemoteDataSource> {
            on { getAllGoods() } doReturn listOf(goodInfo1, goodInfo2)
        }
        val repository = GoodsRepositoryImpl(
            goodsLocalDataSource = localDataSource,
            goodsRemoteDataSource = remoteDataSource,
        )

        val actual = repository.getAllGoods()

        assertEquals(listOf(goodInfo1, goodInfo2), actual)
        verify(localDataSource).getCachedGoods()
        verify(localDataSource).cacheGoods(eq(listOf(goodInfo1, goodInfo2)))
        verify(remoteDataSource).getAllGoods()
    }

    private companion object {
        private val goodInfo1 = GoodInfo(
            id = "good_13647283-dao",
            imageId = "goods-1-img",
            name = "Хлеб с отрубями Хрустяшка",
            producer = GoodProducerInfo(
                id = "prodai-1212",
                name = "АО Московский хрящекомбинат",
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
            rating = 3.2,
        )
        private val goodInfo2 = GoodInfo(
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
    }
}
