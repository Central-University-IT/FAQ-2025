//
//  BonusViewModel+Mock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 11.01.2025.
//

import AppBase

extension BonusViewModel {
    static func mock(
        cashback: String = "",
        bonus: String = ""
    ) -> BonusViewModel {
        BonusViewModel(
            cashback: cashback,
            bonus: bonus
        )
    }
}
