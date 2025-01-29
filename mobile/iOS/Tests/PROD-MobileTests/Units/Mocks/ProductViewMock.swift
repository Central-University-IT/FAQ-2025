//
//  ProductViewMock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 07.01.2025.
//

import AppBase

final class ProductViewMock: UIView, IProductView {

    var invokedConfigure = false
    var invokedConfigureCount = 0
    var invokedConfigureParameters: (product: ProductViewModel, buttonsInteractionModel: IButtonsViewInteractionModel)?
    var invokedConfigureParametersList = [(product: ProductViewModel, buttonsInteractionModel: IButtonsViewInteractionModel)]()

    func configure(product: ProductViewModel, buttonsInteractionModel: IButtonsViewInteractionModel) {
        invokedConfigure = true
        invokedConfigureCount += 1
        invokedConfigureParameters = (product, buttonsInteractionModel)
        invokedConfigureParametersList.append((product, buttonsInteractionModel))
    }

    var invokedPrepareForReuse = false
    var invokedPrepareForReuseCount = 0

    func prepareForReuse() {
        invokedPrepareForReuse = true
        invokedPrepareForReuseCount += 1
    }

    var invokedCartProductDidChanged = false
    var invokedCartProductDidChangedCount = 0

    func cartProductDidChanged() {
        invokedCartProductDidChanged = true
        invokedCartProductDidChangedCount += 1
    }
}
