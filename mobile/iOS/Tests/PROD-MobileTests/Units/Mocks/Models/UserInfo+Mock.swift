//
//  UserInfo+Mock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 07.01.2025.
//

import AppBase

extension UserInfo {
    static func mock(
        lastGoodsCat: [String] = [],
        availableBonuses: [String] = [],
        favourites: [String] = [],
        activityLevel: Int = .zero
    ) -> UserInfo {
        UserInfo(
            lastGoodsCat: lastGoodsCat,
            availableBonuses: availableBonuses,
            favourites: favourites,
            activityLevel: activityLevel
        )
    }
}
