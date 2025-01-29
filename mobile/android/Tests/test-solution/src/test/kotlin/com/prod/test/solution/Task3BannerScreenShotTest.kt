package com.prod.test.solution

import com.prod.core.api.R
import com.prod.core.api.ui.banner.BannerUiModel
import com.prod.core.api.ui.banner.LargeBannerUiModel
import com.prod.core.api.ui.banner.SmallBannerUiModel
import com.prod.solution.impl.ui.banner.BannerView
import org.junit.Test

class Task3BannerScreenShotTest : BaseScreenShotTest() {

    @Test
    fun banners_with_large_titles_score_9() {
        val largeSizeBanner = smallSizeBannerUiModel.copy(
            largeBanner = smallSizeBannerUiModel.largeBanner.copy(
                title = smallSizeBannerUiModel.largeBanner.title.repeat(100),
                description = smallSizeBannerUiModel.largeBanner.description.repeat(100)
            ),
            smallBanner = smallSizeBannerUiModel.smallBanner?.copy(
                leftLabel = smallSizeBannerUiModel.smallBanner!!.leftLabel.repeat(100),
                rightLabel = smallSizeBannerUiModel.smallBanner!!.rightLabel.repeat(100)
            )
        )

        val view = BannerView(paparazzi.context).apply {
            setupBanner(largeSizeBanner)
        }
        paparazzi.snapshot(view)
    }

    @Test
    fun banners_with_small_titles_score_9() {
        val smallSizeBanner = smallSizeBannerUiModel

        val view = BannerView(paparazzi.context).apply {
            setupBanner(smallSizeBanner)
        }
        paparazzi.snapshot(view)
    }

    @Test
    fun first_banner_is_small_banner_score_9() {
        val largeBannerIsFirst = smallSizeBannerUiModel.copy(
            largeBanner = smallSizeBannerUiModel.largeBanner.copy(
                isFirstInPriority = false
            ),
            smallBanner = smallSizeBannerUiModel.smallBanner?.copy(
                isFirstInPriority = true
            )
        )

        val view = BannerView(paparazzi.context).apply {
            setupBanner(largeBannerIsFirst)
        }
        paparazzi.snapshot(view)
    }

    @Test
    fun only_large_banner_score_8() {
        val onlyLargeBanner = smallSizeBannerUiModel.copy(smallBanner = null)

        val view = BannerView(paparazzi.context).apply {
            setupBanner(onlyLargeBanner)
        }
        paparazzi.snapshot(view)
    }

    private val smallSizeBannerUiModel = BannerUiModel(
        largeBanner = LargeBannerUiModel(
            isFirstInPriority = true,
            title = "abc",
            description = "abc",
            imageResId = R.drawable.banner_1_img
        ),
        smallBanner = SmallBannerUiModel(
            isFirstInPriority = false,
            leftLabel = "abc",
            rightLabel = "abc",
            imageResId = R.drawable.bonus_badge
        )
    )
}
