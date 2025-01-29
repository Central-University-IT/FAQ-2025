package com.prod.test.solution.task5

import android.graphics.Color
import com.prod.core.api.R
import com.prod.core.api.domain.models.BonusInfo
import com.prod.core.api.domain.models.GoodInfo
import com.prod.core.api.domain.models.GoodItemQuantityInfo
import com.prod.core.api.domain.models.GoodProducerInfo
import com.prod.core.api.domain.models.PromotionExtra
import com.prod.core.api.domain.models.UserInfo
import com.prod.core.api.ui.goods.GoodsBonusUi
import com.prod.core.api.ui.goods.GoodsFavouriteUi
import com.prod.core.api.ui.goods.GoodsItemUi
import com.prod.solution.impl.mappers.GoodInfoToUiModelMapperImpl
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.Date

class Task5GoodInfoToUiModelMapperImplTest {

    @Test
    fun goodInfoToUiModelMapperImplTest1Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo.copy(
                id = "testId1",
                imageId = "goods-1-img",
                name = "testName1",
                producer = GoodProducerInfo(
                    id = "testProducerId1",
                    name = "testProducerName1",
                )
            ),
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                id = "testId1",
                imageRes = R.drawable.goods_1_img,
                name = "testName1",
                producerName = "testProducerName1",
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestQuantity1Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo.copy(
                goodItemQuantityInfo = GoodItemQuantityInfo(
                    type = "gramm",
                    value = 100,
                )
            ),
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                quantityInfo = "100 г"
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestQuantity2Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo.copy(
                goodItemQuantityInfo = GoodItemQuantityInfo(
                    type = "kilo",
                    value = 8947,
                )
            ),
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                quantityInfo = "8947 кг"
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestCost1Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo.copy(
                imageId = "goods-3-img",
                cost = 3538
            ),
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                cost = "3538 ₽",
                imageRes = R.drawable.goods_3_img,
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestCost2Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo.copy(
                cost = 942
            ),
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                cost = "942 ₽"
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestScore1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo.copy(
                cost = 3538
            ),
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                cost = "3538 ₽"
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestCostAndQuantityInCart1Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo.copy(
                cost = 27935
            ),
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                cost = "27935 ₽",
                costAndQuantityInCart = null
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestCostAndQuantityInCart2Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo.copy(
                cost = 123
            ),
            quantity = 1,
            userInfo = userInfo,
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                cost = "123 ₽",
                costAndQuantityInCart = "1шт. = 123 ₽"
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestCostAndQuantityInCart3Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo.copy(
                cost = 501
            ),
            quantity = 3,
            userInfo = userInfo,
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                cost = "501 ₽",
                costAndQuantityInCart = "3шт. = 1503 ₽"
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestFavourite1Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo.copy(
                isNew = false
            ),
            quantity = 0,
            userInfo = userInfo.copy(
                favorites = listOf(),
            ),
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                favourite = null,
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestFavourite2Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo.copy(
                isNew = true
            ),
            quantity = 0,
            userInfo = userInfo.copy(
                favorites = listOf(),
            ),
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                favourite = GoodsFavouriteUi(
                    text = "Новинка",
                    textColor = Color.parseColor("#F8AA1B"),
                ),
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestFavourite3Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo.copy(
                id = "goodsId1forTest",
                isNew = false
            ),
            quantity = 0,
            userInfo = userInfo.copy(
                favorites = listOf("goodsId1forTest"),
            ),
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                id = "goodsId1forTest",
                favourite = GoodsFavouriteUi(
                    text = "Любимое ❤\uFE0F",
                    textColor = Color.BLACK,
                ),
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestFavourite4Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo.copy(
                id = "goodsId2forTest",
                isNew = true
            ),
            quantity = 0,
            userInfo = userInfo.copy(
                favorites = listOf("goodsId2forTest"),
            ),
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                id = "goodsId2forTest",
                favourite = GoodsFavouriteUi(
                    text = "Любимое ❤\uFE0F",
                    textColor = Color.BLACK,
                ),
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestBonus1Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo,
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = null,
        )

        assertEquals(
            goodsItemUi.copy(
                bonus = null
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestBonus2Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo,
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = BonusInfo(
                id = "bonid1",
                type = "cashback",
                value = 0.05,
                availableDueTo = null,
                promotionExtra = null,
            ),
        )

        assertEquals(
            goodsItemUi.copy(
                bonus = GoodsBonusUi(
                    text = "Кэшбэк 5%",
                    textColor = Color.BLACK,
                    backgroundColor = Color.WHITE,
                    borderColor = null,
                )
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestBonus3Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo,
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = BonusInfo(
                id = "bonid1",
                type = "cashback",
                value = 0.15,
                availableDueTo = Date(),
                promotionExtra = null,
            ),
        )

        assertEquals(
            goodsItemUi.copy(
                bonus = GoodsBonusUi(
                    text = "Кэшбэк 15%",
                    textColor = Color.BLACK,
                    backgroundColor = Color.WHITE,
                    borderColor = null,
                )
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestBonus4Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo,
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = BonusInfo(
                id = "bonid15",
                type = "points",
                value = 300.0,
                availableDueTo = null,
                promotionExtra = null,
            ),
        )

        assertEquals(
            goodsItemUi.copy(
                bonus = GoodsBonusUi(
                    text = "300 баллов",
                    textColor = Color.BLACK,
                    backgroundColor = Color.WHITE,
                    borderColor = null,
                )
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestBonus5Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo,
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = BonusInfo(
                id = "bonid15",
                type = "points",
                value = 3311.0,
                availableDueTo = Date(),
                promotionExtra = null,
            ),
        )

        assertEquals(
            goodsItemUi.copy(
                bonus = GoodsBonusUi(
                    text = "3311 баллов",
                    textColor = Color.BLACK,
                    backgroundColor = Color.WHITE,
                    borderColor = null,
                )
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestBonus6Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo,
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = BonusInfo(
                id = "bonid15",
                type = "points",
                value = 100.0,
                availableDueTo = Date(),
                promotionExtra = PromotionExtra(
                    baseColor = "f3f3f3",
                    tintColor = "FFB1B1",
                    label = "PREMIUM"
                ),
            ),
        )

        assertEquals(
            goodsItemUi.copy(
                bonus = GoodsBonusUi(
                    text = "PREMIUM • 100 Б",
                    textColor = Color.parseColor("#FFB1B1"),
                    backgroundColor = Color.parseColor("#f3f3f3"),
                    borderColor = Color.BLACK,
                )
            ),
            actual,
        )
    }

    @Test
    fun goodInfoToUiModelMapperImplTestBonus7Score1() {
        val mapper = GoodInfoToUiModelMapperImpl()

        val actual = mapper.mapGoodInfoToUiModel(
            goods = goodInfo,
            quantity = 0,
            userInfo = userInfo,
            bonusInfo = BonusInfo(
                id = "bonid30",
                type = "cashback",
                value = 0.30,
                availableDueTo = null,
                promotionExtra = PromotionExtra(
                    baseColor = "f4f3f3",
                    tintColor = "FFB2B1",
                    label = "PREMIUM"
                ),
            ),
        )

        assertEquals(
            goodsItemUi.copy(
                bonus = GoodsBonusUi(
                    text = "PREMIUM • 30%",
                    textColor = Color.parseColor("#FFB2B1"),
                    backgroundColor = Color.parseColor("#f4f3f3"),
                    borderColor = Color.BLACK,
                )
            ),
            actual,
        )
    }

    private companion object {
        val goodsItemUi = GoodsItemUi(
            id = "id1",
            name = "name1",
            imageRes = 0,
            producerName = "someProducer",
            quantityInfo = "100 г",
            cost = "150 ₽",
            costAndQuantityInCart = null,
            favourite = null,
            bonus = null,
        )
        val goodItemQuantityInfo = GoodItemQuantityInfo(
            type = "gramm",
            value = 100,
        )
        val goodInfo = GoodInfo(
            id = "id1",
            imageId = "somerandomimageid",
            name = "name1",
            producer = GoodProducerInfo(
                id = "producerId1",
                name = "someProducer",
            ),
            isNew = false,
            goodItemQuantityInfo = goodItemQuantityInfo,
            cost = 150,
            popularity = 50,
            category = "category1",
            bonusIds = listOf("bon1", "bon2"),
            rating = 4.5,
        )
        val userInfo = UserInfo(
            lastGoodsCategories = listOf("category1", "category2"),
            availableBonuses = listOf("bon2"),
            favorites = listOf(),
            activityLevel = 50,
        )
    }
}
