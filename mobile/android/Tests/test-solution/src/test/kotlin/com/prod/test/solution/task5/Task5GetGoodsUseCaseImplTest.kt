package com.prod.test.solution.task5

import com.prod.core.api.domain.models.BonusInfo
import com.prod.core.api.domain.models.GoodInfo
import com.prod.core.api.domain.models.GoodItemQuantityInfo
import com.prod.core.api.domain.models.GoodProducerInfo
import com.prod.core.api.domain.models.UserInfo
import com.prod.core.api.domain.repositories.CartRepository
import com.prod.core.api.domain.repositories.GoodsRepository
import com.prod.core.api.domain.repositories.UserRepository
import com.prod.core.api.domain.usecases.GetBonusInfoFromGoodInfoUseCase
import com.prod.core.api.domain.usecases.GetGoodsUseCase
import com.prod.core.api.mappers.GoodInfoToUiModelMapper
import com.prod.core.api.sorter.GoodsInfoSorter
import com.prod.core.api.ui.goods.GoodsItemUi
import com.prod.solution.impl.domain.usecases.GetGoodsUseCaseImpl
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

class Task5GetGoodsUseCaseImplTest {

    @Test
    fun getGoodsUseCase_returns_correct_model_and_invokes_dependencies_score_1() = withEnvironment {
        val actual = useCase.getAllGoods()

        assertEquals(
            listOf(goodsItemUi1, goodsItemUi2),
            actual,
        )

        verify(goodsRepository, times(1)).getAllGoods()
        verify(userRepository, times(1)).getUserInfo()
        verify(cartRepository, times(1)).getCart()
        verify(goodsInfoSorter, times(1)).sortGoodsInfo(
            goods = eq(goodsList),
            userInfo = eq(userInfo),
        )

        verify(getBonusInfoFromGoodInfoUseCase).getBonusInfo(eq(goodsList[0]))
        verify(getBonusInfoFromGoodInfoUseCase).getBonusInfo(eq(goodsList[1]))

        verify(goodInfoToUiModelMapper).mapGoodInfoToUiModel(
            goods = eq(goodsList[0]),
            quantity = eq(1),
            userInfo = eq(userInfo),
            bonusInfo = eq(bonusInfo)
        )
        verify(goodInfoToUiModelMapper).mapGoodInfoToUiModel(
            goods = eq(goodsList[1]),
            quantity = eq(0),
            userInfo = eq(userInfo),
            bonusInfo = eq(null)
        )

        verifyNoMoreInteractions(getBonusInfoFromGoodInfoUseCase)
        verifyNoMoreInteractions(goodInfoToUiModelMapper)
    }

    private fun withEnvironment(block: Environment.() -> Unit) {
        val goodsRepository = mock<GoodsRepository> {
            on { getAllGoods() } doReturn goodsList
        }
        val userRepository = mock<UserRepository> {
            on { getUserInfo() } doReturn userInfo
        }
        val cartRepository = mock<CartRepository> {
            on { getCart() } doReturn listOf(goodsList[0] to 1)
        }
        val getBonusInfoFromGoodInfoUseCase = mock<GetBonusInfoFromGoodInfoUseCase> {
            on { getBonusInfo(eq(goodsList[0])) } doReturn bonusInfo
            on { getBonusInfo(eq(goodsList[1])) } doReturn null
        }
        val goodsInfoSorter = mock<GoodsInfoSorter> {
            on { sortGoodsInfo(any(), any()) } doReturn goodsList
        }
        val goodInfoToUiModelMapper = mock<GoodInfoToUiModelMapper> {
            on { mapGoodInfoToUiModel(eq(goodsList[0]), eq(1), any(), any()) } doReturn goodsItemUi1
            on {
                mapGoodInfoToUiModel(
                    eq(goodsList[1]),
                    eq(0),
                    any(),
                    eq(null)
                )
            } doReturn goodsItemUi2
        }

        val useCase = GetGoodsUseCaseImpl(
            goodsRepository = goodsRepository,
            userRepository = userRepository,
            cartRepository = cartRepository,
            getBonusInfoFromGoodInfoUseCase = getBonusInfoFromGoodInfoUseCase,
            goodsInfoSorter = goodsInfoSorter,
            goodInfoToUiModelMapper = goodInfoToUiModelMapper,
        )

        val environment = Environment(
            goodsRepository = goodsRepository,
            userRepository = userRepository,
            cartRepository = cartRepository,
            getBonusInfoFromGoodInfoUseCase = getBonusInfoFromGoodInfoUseCase,
            goodsInfoSorter = goodsInfoSorter,
            goodInfoToUiModelMapper = goodInfoToUiModelMapper,
            useCase = useCase,
        )

        block(environment)
    }

    private class Environment(
        val goodsRepository: GoodsRepository,
        val userRepository: UserRepository,
        val cartRepository: CartRepository,
        val getBonusInfoFromGoodInfoUseCase: GetBonusInfoFromGoodInfoUseCase,
        val goodsInfoSorter: GoodsInfoSorter,
        val goodInfoToUiModelMapper: GoodInfoToUiModelMapper,
        val useCase: GetGoodsUseCase
    )

    private companion object {
        val goodsList = listOf(
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
        )
        val userInfo = UserInfo(
            lastGoodsCategories = listOf(),
            availableBonuses = listOf("bon1", "bon2", "bon3", "bon4"),
            favorites = listOf("6"),
            activityLevel = 10
        )
        val bonusInfo = BonusInfo(
            id = "id2",
            type = "type2",
            value = 111.11,
            availableDueTo = null,
            promotionExtra = null,
        )
        val goodsItemUi1 = GoodsItemUi(
            id = "someID",
            name = "somenameofhleb",
            imageRes = 112233,
            producerName = "SomePrOdUcEr",
            quantityInfo = "100500g",
            cost = "300p",
            costAndQuantityInCart = null,
            favourite = null,
            bonus = null,
        )
        val goodsItemUi2 = GoodsItemUi(
            id = "someID2",
            name = "somenameofhleb2",
            imageRes = 332211,
            producerName = "2SomePrOdUcEr2",
            quantityInfo = "500111g",
            cost = "300300p",
            costAndQuantityInCart = "inCart",
            favourite = null,
            bonus = null,
        )
    }
}
