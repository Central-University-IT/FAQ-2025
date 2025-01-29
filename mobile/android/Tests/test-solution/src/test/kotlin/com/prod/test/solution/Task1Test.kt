package com.prod.test.solution

import com.prod.core.api.data.json.JsonProvider
import com.prod.core.api.data.sources.remote.BannerRemoteDataSource
import com.prod.core.api.domain.models.banner.BannerInfo
import com.prod.core.api.domain.models.banner.LargeBannerInfo
import com.prod.core.api.domain.models.banner.LargeBannerInfo.LargeBannerBonus
import com.prod.core.api.domain.models.banner.SmallBannerInfo
import com.prod.solution.impl.data.sources.remote.BannerRemoteDataSourceImpl
import com.prod.solution.impl.domain.repositories.BannerRepositoryImpl
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class Task1Test {

    @Test
    fun data_source_called_when_repository_called_score_3() {
        val bannerDataSourceMock = mock<BannerRemoteDataSource>()
        val bannerRepository = BannerRepositoryImpl(bannerDataSourceMock)

        bannerRepository.getBannerInfo()

        verify(bannerDataSourceMock).getBanner()
    }

    @Test
    fun json_provider_called_when_data_source_called_score_3() {
        val jsonProviderMock = mock<JsonProvider>() {
            on { bannerInfoJson } doReturn """
                {
                    "large": {
                        "priority": 0,
                        "image_id": "banner-1-img",
                        "title": "Кэшбэк на манго",
                        "description": "Купите до 2 кг Манго и получите %s",
                        "bonus": {
                            "value": 123,
                            "postfix": " баллов"
                        }
                    },
                    "small": {
                        "priority": 1,
                        "right_label": "Акции",
                        "left_label": "2 в 1",
                        "image_id": "bonus-badge"
                    }
                }
            """.trimIndent()
        }
        val bannerDataSource = BannerRemoteDataSourceImpl(jsonProviderMock)

        bannerDataSource.getBanner()

        verify(jsonProviderMock).bannerInfoJson
    }

    @Test
    fun data_source_returns_model_with_full_info_when_JSON_is_full_score_3() {
        val jsonProviderMock = mock<JsonProvider>() {
            on { bannerInfoJson } doReturn """
                {
                    "large": {
                        "priority": 0,
                        "image_id": "banner-1-img",
                        "title": "Кэшбэк на манго",
                        "description": "Купите до 2 кг Манго и получите %s",
                        "bonus": {
                            "value": 123,
                            "postfix": " баллов"
                        }
                    },
                    "small": {
                        "priority": 1,
                        "right_label": "Акции",
                        "left_label": "2 в 1",
                        "image_id": "bonus-badge"
                    }
                }
            """.trimIndent()
        }
        val bannerDataSource = BannerRemoteDataSourceImpl(jsonProviderMock)
        val result = bannerDataSource.getBanner()

        assertEquals(
            BannerInfo(
                large = LargeBannerInfo(
                    priority = 0,
                    imageId = "banner-1-img",
                    title = "Кэшбэк на манго",
                    description = "Купите до 2 кг Манго и получите %s",
                    bonus = LargeBannerBonus(
                        value = 123,
                        postfix = " баллов"
                    )
                ),
                small = SmallBannerInfo(
                    priority = 1,
                    rightLabel = "Акции",
                    leftLabel = "2 в 1",
                    imageId = "bonus-badge"
                )
            ),
            result
        )
    }

    @Test
    fun data_source_returns_model_without_small_banner_when_JSON_doesnt_have_small_banner_score_3() {
        val jsonProviderMock = mock<JsonProvider>() {
            on { bannerInfoJson } doReturn """
                {
                    "large": {
                        "priority": 0,
                        "image_id": "banner-1-img",
                        "title": "Кэшбэк на манго",
                        "description": "Купите до 2 кг Манго и получите %s",
                        "bonus": {
                            "value": 123,
                            "postfix": " баллов"
                        }
                    }
                }
            """.trimIndent()
        }
        val bannerDataSource = BannerRemoteDataSourceImpl(jsonProviderMock)
        val result = bannerDataSource.getBanner()

        assertEquals(
            BannerInfo(
                large = LargeBannerInfo(
                    priority = 0,
                    imageId = "banner-1-img",
                    title = "Кэшбэк на манго",
                    description = "Купите до 2 кг Манго и получите %s",
                    bonus = LargeBannerBonus(
                        value = 123,
                        postfix = " баллов"
                    )
                ),
                small = null
            ),
            result
        )
    }

    @Test
    fun data_source_returns_model_without_bonus_info_when_JSON_doesnt_have_bonus_score_3() {
        val jsonProviderMock = mock<JsonProvider>() {
            on { bannerInfoJson } doReturn """
                {
                    "large": {
                        "priority": 0,
                        "image_id": "banner-1-img",
                        "title": "Кэшбэк на манго",
                        "description": "Купите до 2 кг Манго и получите %s"
                    },
                    "small": {
                        "priority": 1,
                        "right_label": "Акции",
                        "left_label": "2 в 1",
                        "image_id": "bonus-badge"
                    }
                }
            """.trimIndent()
        }
        val bannerDataSource = BannerRemoteDataSourceImpl(jsonProviderMock)
        val result = bannerDataSource.getBanner()

        assertEquals(
            BannerInfo(
                large = LargeBannerInfo(
                    priority = 0,
                    imageId = "banner-1-img",
                    title = "Кэшбэк на манго",
                    description = "Купите до 2 кг Манго и получите %s",
                    bonus = null
                ),
                small = SmallBannerInfo(
                    priority = 1,
                    rightLabel = "Акции",
                    leftLabel = "2 в 1",
                    imageId = "bonus-badge"
                )
            ),
            result
        )
    }
}
