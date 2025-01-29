//
//  GoodsBanner+Stub.swift
//  PROD-Mobile
//
//  Created by m.titor on 07.01.2025.
//

import AppBase

extension GoodsBanner {
    static func stub(
        largeBannerPriority: Int = 1,
        largeBannerImageId: String = "banner-1-img",
        largeBannerTitle: String = "Кэшбэк на манго",
        largeBannerDescription: String = "Купите до 2 кг Манго и получите %@",
        largeBannerBonusValue: Int? = 123,
        largeBannerBonusPostfix: String? = " баллов",
        smallBannerPriority: Int? = 0,
        smallBannerRightLabel: String = "Акция",
        smallBannerLeftLabel: String = "2 в 1"
    ) -> GoodsBanner {
        switch (smallBannerPriority, largeBannerBonusValue) {
        case (let smallBannerPriority?, let largeBannerBonusValue?):
            return GoodsBanner(
                largeBanner: LargeBanner(
                    priority: largeBannerPriority,
                    imageId: largeBannerImageId,
                    title: largeBannerTitle,
                    description: largeBannerDescription,
                    bonus: LargeBannerBonus(
                        value: largeBannerBonusValue,
                        postfix: largeBannerBonusPostfix!
                    )
                ),
                smallBanner: SmallBanner(
                    priority: smallBannerPriority,
                    rightLabel: smallBannerRightLabel,
                    leftLabel: smallBannerLeftLabel
                )
            )
        case (let smallBannerPriority?, nil):
            return GoodsBanner(
                largeBanner: LargeBanner(
                    priority: largeBannerPriority,
                    imageId: largeBannerImageId,
                    title: largeBannerTitle,
                    description: largeBannerDescription,
                    bonus: nil
                ),
                smallBanner: SmallBanner(
                    priority: smallBannerPriority,
                    rightLabel: smallBannerRightLabel,
                    leftLabel: smallBannerLeftLabel
                )
            )
        case (nil, let largeBannerBonusValue?):
            return GoodsBanner(
                largeBanner: LargeBanner(
                    priority: largeBannerPriority,
                    imageId: largeBannerImageId,
                    title: largeBannerTitle,
                    description: largeBannerDescription,
                    bonus: LargeBannerBonus(
                        value: largeBannerBonusValue,
                        postfix: largeBannerBonusPostfix!
                    )
                ),
                smallBanner: nil
            )
        case (nil, nil):
            return GoodsBanner(
                largeBanner: LargeBanner(
                    priority: largeBannerPriority,
                    imageId: largeBannerImageId,
                    title: largeBannerTitle,
                    description: largeBannerDescription,
                    bonus: nil
                ),
                smallBanner: nil
            )
        }
    }
}
