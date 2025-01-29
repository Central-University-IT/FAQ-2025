//
//  Bonus+Mock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 11.01.2025.
//

import AppBase

extension Bonus {
    static func mock(
        id: String = "",
        type: BonusType = .cashback,
        value: Double = .zero,
        promotionExtra: Promotion? = nil,
        availableDueTo: String? = nil
    ) -> Bonus {
        Bonus(
            id: id,
            type: type,
            value: value,
            promotionExtra: promotionExtra,
            availableDueTo: availableDueTo
        )
    }
}
