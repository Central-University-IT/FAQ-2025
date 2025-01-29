//
//  ProductViewModel+Mock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 07.01.2025.
//

import AppBase

extension ProductViewModel {
    static func mock(
        name: String = "",
        producer: String = "",
        weight: String = "",
        price: String = "",
        image: UIImage = UIImage(),
        cost: Double = .zero,
        score: Int = .zero,
        bottomBadge: Badge? = nil,
        topBadge: Badge? = nil,
        buttonsText: @escaping (Int) -> String = { _ in "" }
    ) -> ProductViewModel {
        ProductViewModel(
            id: "id",
            name: name,
            producer: producer,
            weight: weight,
            price: price,
            image: image,
            cost: cost,
            score: score,
            bottomBadge: bottomBadge,
            topBadge: topBadge,
            buttonsText: buttonsText
        )
    }
}
