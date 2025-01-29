package com.prod.test.solution

import com.prod.core.api.R
import com.prod.core.api.domain.models.banner.BannerInfo
import com.prod.core.api.domain.models.banner.LargeBannerInfo
import com.prod.core.api.domain.models.banner.LargeBannerInfo.LargeBannerBonus
import com.prod.core.api.domain.models.banner.SmallBannerInfo
import com.prod.core.api.ui.banner.BannerUiModel
import com.prod.core.api.ui.banner.LargeBannerUiModel
import com.prod.core.api.ui.banner.SmallBannerUiModel
import com.prod.solution.impl.mappers.BannerInfoToUiModelMapperImpl
import junit.framework.TestCase.assertEquals
import org.junit.Test

class Task2Test {

    @Test
    fun mapper_return_large_banner_in_priority_when_large_priority_bigger_than_small_priority_score_2() {
        val bannerInfo = BannerInfo(
            large = LargeBannerInfo(
                priority = 1,
                imageId = "banner-1-img",
                title = "Кэшбэк на манго",
                description = "Купите до 2 кг Манго и получите",
                bonus = null
            ),
            small = SmallBannerInfo(
                priority = 0,
                rightLabel = "Акции",
                leftLabel = "2 в 1",
                imageId = "bonus-badge"
            )
        )

        val result = BannerInfoToUiModelMapperImpl().mapBannerInfoToUiModel(bannerInfo)

        assertEquals(
            result,
            BannerUiModel(
                largeBanner = LargeBannerUiModel(
                    isFirstInPriority = true,
                    title = "Кэшбэк на манго",
                    description = "Купите до 2 кг Манго и получите",
                    imageResId = R.drawable.banner_1_img
                ),
                smallBanner = SmallBannerUiModel(
                    isFirstInPriority = false,
                    rightLabel = "Акции",
                    leftLabel = "2 в 1",
                    imageResId = R.drawable.bonus_badge
                )
            )
        )
    }

    @Test
    fun mapper_return_small_banner_in_priority_when_large_priority_smaller_than_small_priority_score_2() {
        val bannerInfo = BannerInfo(
            large = LargeBannerInfo(
                priority = 0,
                imageId = "banner-1-img",
                title = "Кэшбэк на манго",
                description = "Купите до 2 кг Манго и получите",
                bonus = null
            ),
            small = SmallBannerInfo(
                priority = 1,
                rightLabel = "Акции",
                leftLabel = "2 в 1",
                imageId = "bonus-badge"
            )
        )

        val result = BannerInfoToUiModelMapperImpl().mapBannerInfoToUiModel(bannerInfo)

        assertEquals(
            result,
            BannerUiModel(
                largeBanner = LargeBannerUiModel(
                    isFirstInPriority = false,
                    title = "Кэшбэк на манго",
                    description = "Купите до 2 кг Манго и получите",
                    imageResId = R.drawable.banner_1_img
                ),
                smallBanner = SmallBannerUiModel(
                    isFirstInPriority = true,
                    rightLabel = "Акции",
                    leftLabel = "2 в 1",
                    imageResId = R.drawable.bonus_badge
                )
            )
        )
    }

    @Test
    fun mapper_return_large_banner_in_priority_when_large_and_small_priorities_same_score_2() {
        val bannerInfo = BannerInfo(
            large = LargeBannerInfo(
                priority = 0,
                imageId = "banner-1-img",
                title = "Кэшбэк на манго",
                description = "Купите до 2 кг Манго и получите",
                bonus = null
            ),
            small = SmallBannerInfo(
                priority = 0,
                rightLabel = "Акции",
                leftLabel = "2 в 1",
                imageId = "bonus-badge"
            )
        )

        val result = BannerInfoToUiModelMapperImpl().mapBannerInfoToUiModel(bannerInfo)

        assertEquals(
            result,
            BannerUiModel(
                largeBanner = LargeBannerUiModel(
                    isFirstInPriority = true,
                    title = "Кэшбэк на манго",
                    description = "Купите до 2 кг Манго и получите",
                    imageResId = R.drawable.banner_1_img
                ),
                smallBanner = SmallBannerUiModel(
                    isFirstInPriority = false,
                    rightLabel = "Акции",
                    leftLabel = "2 в 1",
                    imageResId = R.drawable.bonus_badge
                )
            )
        )
    }

    @Test
    fun mapper_return_banner_large_banner_with_banner_1_img_and_small_banner_with_bonus_badge_when_domain_model_have_large_banner_banner_1_img_and_small_banner_bonus_badge_images_score_1() {
        val bannerInfo = BannerInfo(
            large = LargeBannerInfo(
                priority = 0,
                imageId = "banner-1-img",
                title = "Кэшбэк на манго",
                description = "Купите до 2 кг Манго и получите",
                bonus = null
            ),
            small = SmallBannerInfo(
                priority = 0,
                rightLabel = "Акции",
                leftLabel = "2 в 1",
                imageId = "bonus-badge"
            )
        )

        val result = BannerInfoToUiModelMapperImpl().mapBannerInfoToUiModel(bannerInfo)

        assertEquals(
            result,
            BannerUiModel(
                largeBanner = LargeBannerUiModel(
                    isFirstInPriority = true,
                    title = "Кэшбэк на манго",
                    description = "Купите до 2 кг Манго и получите",
                    imageResId = R.drawable.banner_1_img
                ),
                smallBanner = SmallBannerUiModel(
                    isFirstInPriority = false,
                    rightLabel = "Акции",
                    leftLabel = "2 в 1",
                    imageResId = R.drawable.bonus_badge
                )
            )
        )
    }

    @Test
    fun mapper_return_banner_large_banner_with_bonus_badge_and_small_banner_with_banner_1_img_when_domain_model_have_large_banner_bonus_badge_and_small_banner_banner_1_img_images_score_2() {
        val bannerInfo = BannerInfo(
            large = LargeBannerInfo(
                priority = 0,
                imageId = "bonus-badge",
                title = "Кэшбэк на манго",
                description = "Купите до 2 кг Манго и получите",
                bonus = null
            ),
            small = SmallBannerInfo(
                priority = 0,
                rightLabel = "Акции",
                leftLabel = "2 в 1",
                imageId = "banner-1-img"
            )
        )

        val result = BannerInfoToUiModelMapperImpl().mapBannerInfoToUiModel(bannerInfo)

        assertEquals(
            result,
            BannerUiModel(
                largeBanner = LargeBannerUiModel(
                    isFirstInPriority = true,
                    title = "Кэшбэк на манго",
                    description = "Купите до 2 кг Манго и получите",
                    imageResId = R.drawable.bonus_badge
                ),
                smallBanner = SmallBannerUiModel(
                    isFirstInPriority = false,
                    rightLabel = "Акции",
                    leftLabel = "2 в 1",
                    imageResId = R.drawable.banner_1_img
                )
            )
        )
    }

    @Test
    fun mapper_return_only_large_model_when_domain_model_doesnt_have_small_banner_score_2() {
        val bannerInfo = BannerInfo(
            large = LargeBannerInfo(
                priority = 1,
                imageId = "banner-1-img",
                title = "Кэшбэк на манго",
                description = "Купите до 2 кг Манго и получите",
                bonus = null
            ),
            small = null
        )

        val result = BannerInfoToUiModelMapperImpl().mapBannerInfoToUiModel(bannerInfo)

        assertEquals(
            result,
            BannerUiModel(
                largeBanner = LargeBannerUiModel(
                    isFirstInPriority = true,
                    title = "Кэшбэк на манго",
                    description = "Купите до 2 кг Манго и получите",
                    imageResId = R.drawable.banner_1_img
                ),
                smallBanner = null
            )
        )
    }

    @Test
    fun mapper_return_large_banner_without_bonus_when_domain_model_doesnt_have_bonus_score_2() {
        val bannerInfo = BannerInfo(
            large = LargeBannerInfo(
                priority = 1,
                imageId = "banner-1-img",
                title = "Кэшбэк на манго",
                description = "Купите до 2 кг Манго и получите",
                bonus = null
            ),
            small = null
        )

        val result = BannerInfoToUiModelMapperImpl().mapBannerInfoToUiModel(bannerInfo)

        assertEquals(
            result,
            BannerUiModel(
                largeBanner = LargeBannerUiModel(
                    isFirstInPriority = true,
                    title = "Кэшбэк на манго",
                    description = "Купите до 2 кг Манго и получите",
                    imageResId = R.drawable.banner_1_img
                ),
                smallBanner = null
            )
        )
    }

    @Test
    fun mapper_return_large_banner_with_bonus_when_domain_model_have_bonus_score_2() {
        val bannerInfo = BannerInfo(
            large = LargeBannerInfo(
                priority = 1,
                imageId = "banner-1-img",
                title = "Кэшбэк на манго",
                description = "Купите до 2 кг Манго и получите %s",
                bonus = LargeBannerBonus(
                    value = 123,
                    postfix = " баллов"
                )
            ),
            small = null
        )

        val result = BannerInfoToUiModelMapperImpl().mapBannerInfoToUiModel(bannerInfo)

        assertEquals(
            result,
            BannerUiModel(
                largeBanner = LargeBannerUiModel(
                    isFirstInPriority = true,
                    title = "Кэшбэк на манго 123 баллов",
                    description = "Купите до 2 кг Манго и получите 123 баллов",
                    imageResId = R.drawable.banner_1_img
                ),
                smallBanner = null
            )
        )
    }
}
