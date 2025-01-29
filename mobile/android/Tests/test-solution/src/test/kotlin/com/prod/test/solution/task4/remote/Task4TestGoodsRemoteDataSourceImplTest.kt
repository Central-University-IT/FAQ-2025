package com.prod.test.solution.task4.remote

import com.prod.core.api.data.json.JsonProvider
import com.prod.core.api.domain.models.GoodInfo
import com.prod.core.api.domain.models.GoodItemQuantityInfo
import com.prod.core.api.domain.models.GoodProducerInfo
import com.prod.solution.impl.data.sources.remote.GoodsRemoteDataSourceImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class Task4TestGoodsRemoteDataSourceImplTest {

    @Test
    fun jsonProvider_called_when_data_source_called_score_2() {
        val jsonProviderMock = mock<JsonProvider> {
            on { allGoodsJson } doReturn """
                {
                    "goods": []
                }
            """.trimIndent()
        }
        val goodsRemoteDataSource = GoodsRemoteDataSourceImpl(jsonProviderMock)

        val goodsList = goodsRemoteDataSource.getAllGoods()

        assertTrue(goodsList.isEmpty())
        verify(jsonProviderMock).allGoodsJson
    }

    @Test
    fun data_source_returns_model_with_full_info_when_JSON_is_full_score_2() {
        val jsonProviderMock = mock<JsonProvider> {
            on { allGoodsJson } doReturn """
                {
                    "goods": [
                        {
                            "id": "good_13647283-dao",
                            "image_id": "goods-1-img",
                            "name": "Хлеб с отрубями Хрустяшка",
                            "producer": {
                                "id": "prodai-1212",
                                "name": "АО Московский хрящекомбинат"
                            },
                            "is_new": false,
                            "item_countity": {
                                "type": "gramm",
                                "value": 200
                            },
                            "cost": 130,
                            "popularity": 100,
                            "category": "HLEB",
                            "bonus_ids": ["ada", "dad"],
                            "rating": 3.2
                        }                        
                    ]
                }
            """.trimIndent()
        }
        val goodsRemoteDataSource = GoodsRemoteDataSourceImpl(jsonProviderMock)

        val goodsList = goodsRemoteDataSource.getAllGoods()

        assertTrue(goodsList.size == 1)
        assertEquals(
            listOf(
                GoodInfo(
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
            ),
            goodsList,
        )
    }

    @Test
    fun data_source_returns_model_with_empty_list_of_bonusIds_when_JSON_without_bonus_ids_score_2() {
        val jsonProviderMock = mock<JsonProvider> {
            on { allGoodsJson } doReturn """
                {
                    "goods": [
                        {
                            "id": "good_13647283-dao",
                            "image_id": "goods-1-img",
                            "name": "Хлеб с отрубями Хрустяшка",
                            "producer": {
                                "id": "prodai-31323",
                                "name": "АО Московский хрящекомбинат"
                            },
                            "is_new": false,
                            "item_countity": {
                                "type": "gramm",
                                "value": 200
                            },
                            "cost": 130,
                            "popularity": 100,
                            "category": "HLEB",
                            "rating": 4.9
                        }                        
                    ]
                }
            """.trimIndent()
        }
        val goodsRemoteDataSource = GoodsRemoteDataSourceImpl(jsonProviderMock)

        val goodsList = goodsRemoteDataSource.getAllGoods()

        assertTrue(goodsList.size == 1)
        assertEquals(
            listOf(
                GoodInfo(
                    id = "good_13647283-dao",
                    imageId = "goods-1-img",
                    name = "Хлеб с отрубями Хрустяшка",
                    producer = GoodProducerInfo(
                        id = "prodai-31323",
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
                    bonusIds = emptyList(),
                    rating = 4.9,
                )
            ),
            goodsList,
        )
    }

    @Test
    fun data_source_returns_model_with_empty_list_of_bonusIds_when_JSON_bonus_ids_is_empty_score_2() {
        val jsonProviderMock = mock<JsonProvider> {
            on { allGoodsJson } doReturn """
                {
                    "goods": [
                        {
                            "id": "good_13647283-dao",
                            "image_id": "goods-1-img",
                            "name": "Хлеб с отрубями Хрустяшка",
                            "producer": {
                                "id": "prodai-31323",
                                "name": "АО Московский хрящекомбинат"
                            },
                            "is_new": true,
                            "item_countity": {
                                "type": "gramm",
                                "value": 200
                            },
                            "cost": 130,
                            "popularity": 100,
                            "category": "HLEB",
                            "bonus_ids": [],
                            "rating": 5.0
                        }                        
                    ]
                }
            """.trimIndent()
        }
        val goodsRemoteDataSource = GoodsRemoteDataSourceImpl(jsonProviderMock)

        val goodsList = goodsRemoteDataSource.getAllGoods()

        assertTrue(goodsList.size == 1)
        assertEquals(
            listOf(
                GoodInfo(
                    id = "good_13647283-dao",
                    imageId = "goods-1-img",
                    name = "Хлеб с отрубями Хрустяшка",
                    producer = GoodProducerInfo(
                        id = "prodai-31323",
                        name = "АО Московский хрящекомбинат",
                    ),
                    isNew = true,
                    goodItemQuantityInfo = GoodItemQuantityInfo(
                        type = "gramm",
                        value = 200,
                    ),
                    cost = 130,
                    popularity = 100,
                    category = "HLEB",
                    bonusIds = emptyList(),
                    rating = 5.0,
                )
            ),
            goodsList,
        )
    }

    @Test
    fun data_source_returns_model_with_rating_equals_null_when_JSON_not_have_rating_score_2() {
        val jsonProviderMock = mock<JsonProvider> {
            on { allGoodsJson } doReturn """
                {
                    "goods": [
                        {
                            "id": "good_13647283-dao",
                            "image_id": "goods-1-img",
                            "name": "Хлеб с отрубями Хрустяшка",
                            "producer": {
                                "id": "prodai-31323",
                                "name": "АО Московский хрящекомбинат"
                            },
                            "is_new": true,
                            "item_countity": {
                                "type": "gramm",
                                "value": 200
                            },
                            "cost": 130,
                            "popularity": 100,
                            "category": "HLEB",
                            "bonus_ids": ["ada"]
                        }                        
                    ]
                }
            """.trimIndent()
        }
        val goodsRemoteDataSource = GoodsRemoteDataSourceImpl(jsonProviderMock)

        val goodsList = goodsRemoteDataSource.getAllGoods()

        assertTrue(goodsList.size == 1)
        assertEquals(
            listOf(
                GoodInfo(
                    id = "good_13647283-dao",
                    imageId = "goods-1-img",
                    name = "Хлеб с отрубями Хрустяшка",
                    producer = GoodProducerInfo(
                        id = "prodai-31323",
                        name = "АО Московский хрящекомбинат",
                    ),
                    isNew = true,
                    goodItemQuantityInfo = GoodItemQuantityInfo(
                        type = "gramm",
                        value = 200,
                    ),
                    cost = 130,
                    popularity = 100,
                    category = "HLEB",
                    bonusIds = listOf("ada"),
                    rating = null,
                )
            ),
            goodsList,
        )
    }

    @Test
    fun data_source_returns_model_with_isNew_equals_false_when_JSON_not_have_is_new_score_2() {
        val jsonProviderMock = mock<JsonProvider> {
            on { allGoodsJson } doReturn """
                {
                    "goods": [
                        {
                            "id": "good_13647283-dao",
                            "image_id": "goods-1-img",
                            "name": "Хлеб с отрубями Хрустяшка",
                            "producer": {
                                "id": "prodai-31323",
                                "name": "АО Московский хрящекомбинат 2"
                            },
                            "item_countity": {
                                "type": "gramm",
                                "value": 201
                            },
                            "cost": 130,
                            "popularity": 100,
                            "category": "HLEB",
                            "bonus_ids": ["ada", "dad"]
                        }                        
                    ]
                }
            """.trimIndent()
        }
        val goodsRemoteDataSource = GoodsRemoteDataSourceImpl(jsonProviderMock)

        val goodsList = goodsRemoteDataSource.getAllGoods()

        assertTrue(goodsList.size == 1)
        assertEquals(
            listOf(
                GoodInfo(
                    id = "good_13647283-dao",
                    imageId = "goods-1-img",
                    name = "Хлеб с отрубями Хрустяшка",
                    producer = GoodProducerInfo(
                        id = "prodai-31323",
                        name = "АО Московский хрящекомбинат 2",
                    ),
                    isNew = false,
                    goodItemQuantityInfo = GoodItemQuantityInfo(
                        type = "gramm",
                        value = 201,
                    ),
                    cost = 130,
                    popularity = 100,
                    category = "HLEB",
                    bonusIds = listOf("ada", "dad"),
                    rating = null,
                )
            ),
            goodsList,
        )
    }

    @Test
    fun data_source_returns_2_models_with_full_info_when_JSON_is_full_and_has_2_objects_score_3() {
        val jsonProviderMock = mock<JsonProvider> {
            on { allGoodsJson } doReturn """
                {
                    "goods": [
                        {
                            "id": "good_13647283-dao",
                            "image_id": "goods-1-img",
                            "name": "Хлеб с отрубями Хрустяшка",
                            "producer": {
                                "id": "prodai-1212",
                                "name": "АО Московский хрящекомбинат"
                            },
                            "is_new": false,
                            "item_countity": {
                                "type": "gramm",
                                "value": 200
                            },
                            "cost": 130,
                            "popularity": 100,
                            "category": "HLEB",
                            "bonus_ids": ["ada", "dad"],
                            "rating": 3.2
                        },
                        {
                            "id": "good_13649783-wpo",
                            "image_id": "goods-4-img",
                            "name": "Манго сочное. Египет",
                            "producer": {
                                "id": "prodai-31323",
                                "name": "Египетские сельхоз проля Дары Клеопатры"
                            },
                            "item_countity": {
                                "type": "kilo",
                                "value": 3
                            },
                            "cost": 250,
                            "popularity": 35,
                            "rating": 4.5,
                            "bonus_ids": ["bonus_13132", "bonus_4243"],
                            "category": "FRUITS"
                        }
                    ]
                }
            """.trimIndent()
        }
        val goodsRemoteDataSource = GoodsRemoteDataSourceImpl(jsonProviderMock)

        val goodsList = goodsRemoteDataSource.getAllGoods()

        assertTrue(goodsList.size == 2)
        assertEquals(
            listOf(
                GoodInfo(
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
                ),
                GoodInfo(
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
                ),
            ),
            goodsList,
        )
    }
}
