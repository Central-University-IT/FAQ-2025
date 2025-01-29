//
//  CartProductViewModel+Mock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 11.01.2025.
//

import AppBase

extension CartProductViewModel {
    static func mock(
        id: String = "",
        name: String = "",
        totalWeight: String = "",
        totalPrice: String = "",
        priceDescription: String = "",
        image: UIImage = UIImage()
    ) -> CartProductViewModel {
        CartProductViewModel(
            id: id,
            name: name,
            totalWeight: totalWeight,
            totalPrice: totalPrice,
            priceDescription: priceDescription,
            image: image
        )
    }
}
