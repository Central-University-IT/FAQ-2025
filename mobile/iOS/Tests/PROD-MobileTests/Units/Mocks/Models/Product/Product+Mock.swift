//
//  Product+Mock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 07.01.2025.
//

import AppBase

extension Product {
    static func mock(
        id: String = "",
        name: String = "",
        imageId: String = "",
        category: String = "",
        cost: Double = .zero,
        isNew: Bool = false,
        popularity: Int = .zero,
        bonusIds: [String]? = nil,
        rating: Double? = nil,
        itemCountity: Product.ItemCountity = Product.ItemCountity(type: .kilo, value: .zero)
    ) -> Product {
        Product(
            id: id,
            name: name,
            imageId: imageId,
            producer: Product.Producer(id: "id", name: "name"),
            itemCountity: itemCountity,
            cost: cost,
            popularity: popularity,
            category: category,
            rating: rating,
            isNew: isNew,
            bonusIds: bonusIds
        )
    }
}
