package com.prod.test.solution.task5

import com.prod.core.api.domain.models.BonusInfo
import com.prod.core.api.domain.models.GoodInfo
import com.prod.core.api.domain.models.GoodItemQuantityInfo
import com.prod.core.api.domain.models.GoodProducerInfo
import com.prod.core.api.domain.models.PromotionExtra
import com.prod.core.api.domain.models.UserInfo
import com.prod.core.api.domain.repositories.BonusesRepository
import com.prod.core.api.domain.repositories.UserRepository
import com.prod.core.api.domain.usecases.GetBonusInfoFromGoodInfoUseCase
import com.prod.core.api.provider.CurrentDateProvider
import com.prod.solution.impl.domain.usecases.GetBonusInfoFromGoodInfoUseCaseImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.util.Date

class Task5GetBonusInfoFromGoodInfoUseCaseImplTest {

    @Test
    fun useCase_invokes_repositories_when_call_getBonusInfo_score_1() = withEnvironment(
        bonuses = listOf(),
        goodInfoBonusIds = listOf(),
        userAvailableBonuses = listOf(),
    ) {
        val actual = useCase.getBonusInfo(goodInfo)

        verify(bonusesRepository, times(1)).getAllBonuses()
        verify(userRepository, times(1)).getUserInfo()
        assertNull(actual)
    }

    @Test
    fun useCase_returns_first_item_when_all_items_have_availableDueTo_score_1() = withEnvironment(
        bonuses = listOf(
            bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
            bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
            bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
        ),
        goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
        userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
    ) {
        val actual = useCase.getBonusInfo(goodInfo)

        verify(bonusesRepository, times(1)).getAllBonuses()
        verify(userRepository, times(1)).getUserInfo()

        assertEquals(
            bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
            actual,
        )
    }

    @Test
    fun useCase_returns_first_item_when_first_item_not_have_availableDueTo_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = null),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = null),
                actual,
            )
        }

    @Test
    fun useCase_returns_second_item_when_first_item_availableDueTo_before_current_date_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_third_item_when_only_third_item_have_availableDueTo_equals_afterCurrentDate_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_second_item_when_second_item_has_availableDueTo_equals_null_and_third_afterCurrentDate_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = null),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = null),
                actual,
            )
        }

    @Test
    fun useCase_returns_null_when_no_item_with_availableDueTo_equals_afterCurrentDate_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = beforeCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                null,
                actual,
            )
        }

    @Test
    fun useCase_returns_first_item_when_all_items_with_availableDueTo_equals_null_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = null),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = null),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = null),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = null),
                actual,
            )
        }

    @Test
    fun useCase_returns_second_item_when_good_info_not_have_first_bonus_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_third_item_when_good_info_have_only_third_bonus_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_null_when_good_info_not_have_any_bonuses_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf(),
            userAvailableBonuses = listOf("somebonus3", "somebonus1", "somebonus2"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                null,
                actual,
            )
        }

    @Test
    fun useCase_returns_second_item_when_user_info_not_have_first_bonus_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus3", "somebonus2"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_third_item_when_user_info_have_only_third_bonus_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_null_when_user_info_not_have_any_bonuses_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf(),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                null,
                actual,
            )
        }

    @Test
    fun useCase_returns_null_when_getBonusInfo_if_some_ids_not_contained_in_lists_test_1_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                null,
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_some_ids_not_contained_in_lists_test_2_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_some_ids_not_contained_in_lists_test_3_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = null),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_null_when_getBonusInfo_if_some_ids_not_contained_in_lists_test_4_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = beforeCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus3"),
            userAvailableBonuses = listOf("somebonus2"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                null,
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_some_ids_not_contained_in_lists_test_5_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus3", "somebonus1"),
            userAvailableBonuses = listOf("somebonus2", "somebonus1"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                null,
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_some_ids_not_contained_in_lists_test_6_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus3"),
            userAvailableBonuses = listOf("somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_null_when_getBonusInfo_if_some_ids_not_contained_in_lists_test_7_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = beforeCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus3"),
            userAvailableBonuses = listOf("somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                null,
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_some_ids_not_contained_in_lists_test_8_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus3"),
            userAvailableBonuses = listOf("somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_availableDueTo_is_past_test_1_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_availableDueTo_is_past_test_2_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_availableDueTo_is_past_test_3_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = beforeCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_availableDueTo_is_past_test_5_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_availableDueTo_is_past_test_6_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = beforeCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_availableDueTo_is_past_test_7_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = null),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_availableDueTo_is_past_test_8_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = beforeCurrentDate),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = null),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = null),
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_availableDueTo_is_past_test_9_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = null),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = null),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus2", "somebonus3"),
            userAvailableBonuses = listOf("somebonus1", "somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = null),
                actual,
            )
        }

    @Test
    fun useCase_returns_item_when_getBonusInfo_if_availableDueTo_is_past_test_10_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = null),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = null),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus3"),
            userAvailableBonuses = listOf("somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = afterCurrentDate),
                actual,
            )
        }

    @Test
    fun useCase_returns_null_when_getBonusInfo_if_availableDueTo_is_past_test_11_score_1() =
        withEnvironment(
            bonuses = listOf(
                bonusInfoTemplate.copy(id = "somebonus1", availableDueTo = null),
                bonusInfoTemplate.copy(id = "somebonus2", availableDueTo = null),
                bonusInfoTemplate.copy(id = "somebonus3", availableDueTo = beforeCurrentDate),
            ),
            goodInfoBonusIds = listOf("somebonus1", "somebonus3"),
            userAvailableBonuses = listOf("somebonus2", "somebonus3"),
        ) {
            val actual = useCase.getBonusInfo(goodInfo)

            verify(bonusesRepository, times(1)).getAllBonuses()
            verify(userRepository, times(1)).getUserInfo()

            assertEquals(
                null,
                actual,
            )
        }

    private fun withEnvironment(
        bonuses: List<BonusInfo>,
        goodInfoBonusIds: List<String>,
        userAvailableBonuses: List<String>,
        block: Environment.() -> Unit,
    ) {
        val currentDateProvider = object : CurrentDateProvider {
            override fun provideCurrentDate(): Date {
                return currentDate
            }
        }
        val bonusesRepository = mock<BonusesRepository> {
            on { getAllBonuses() } doReturn bonuses
        }
        val userRepository = mock<UserRepository> {
            on { getUserInfo() } doReturn userInfoTemplate.copy(
                availableBonuses = userAvailableBonuses
            )
        }
        val useCase = GetBonusInfoFromGoodInfoUseCaseImpl(
            bonusesRepository = bonusesRepository,
            userRepository = userRepository,
            currentDateProvider = currentDateProvider,
        )
        val environment = Environment(
            bonusesRepository = bonusesRepository,
            userRepository = userRepository,
            useCase = useCase,
            goodInfo = goodInfoTemplate.copy(
                bonusIds = goodInfoBonusIds,
            ),
        )
        environment.block()
    }

    private class Environment(
        val bonusesRepository: BonusesRepository,
        val userRepository: UserRepository,
        val useCase: GetBonusInfoFromGoodInfoUseCase,
        val goodInfo: GoodInfo,
    )

    private companion object {
        private val goodInfoTemplate = GoodInfo(
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
            popularity = 11,
            category = "HLEBUSHEK",
            bonusIds = emptyList(),
            rating = null,
        )
        private val bonusInfoTemplate = BonusInfo(
            id = "",
            type = "points",
            value = 100.0,
            availableDueTo = null,
            promotionExtra = PromotionExtra(
                baseColor = "000000",
                tintColor = "F3F3F3",
                label = "PREMIUM",
            ),
        )
        private val userInfoTemplate = UserInfo(
            lastGoodsCategories = listOf("MILK"),
            availableBonuses = emptyList(),
            favorites = listOf("7"),
            activityLevel = 55,
        )
        private val currentDate = Date(1737044731198) // UTC 'Thu Jan 16 2025 16:25:31.198'
        private val afterCurrentDate = Date(1745421943000) //UTC 'Wed Apr 23 2025 15:25:43.000'
        private val beforeCurrentDate = Date(1713885943000) //UTC 'Tue Apr 23 2024 15:25:43.000'
    }
}
