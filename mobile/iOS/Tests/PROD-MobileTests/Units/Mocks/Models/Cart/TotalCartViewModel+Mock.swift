//
//  TotalCartViewModel+Mock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 11.01.2025.
//

import AppBase

extension TotalCartViewModel {
    static func mock(
        price: String = "",
        weight: String = ""
    ) -> TotalCartViewModel {
        TotalCartViewModel(
            price: price,
            weight: weight
        )
    }
}
