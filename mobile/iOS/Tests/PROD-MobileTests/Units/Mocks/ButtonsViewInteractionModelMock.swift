//
//  ButtonsViewInteractionModelMock.swift
//  PROD-Mobile
//
//  Created by Kuznetsov Mikhail on 16.01.2025.
//

import AppBase

final class ButtonsViewInteractionModelMock: IButtonsViewInteractionModel {

    var invokedQuantityGetter = false
    var invokedQuantityGetterCount = 0
    var stubbedQuantity: Int! = 0

    var quantity: Int {
        invokedQuantityGetter = true
        invokedQuantityGetterCount += 1
        return stubbedQuantity
    }

    var invokedOnPressedAdd = false
    var invokedOnPressedAddCount = 0

    func onPressedAdd() {
        invokedOnPressedAdd = true
        invokedOnPressedAddCount += 1
    }

    var invokedOnPressedRemove = false
    var invokedOnPressedRemoveCount = 0

    func onPressedRemove() {
        invokedOnPressedRemove = true
        invokedOnPressedRemoveCount += 1
    }
}
