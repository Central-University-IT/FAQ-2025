//
//  CartProductDelegateMock.swift
//  PROD-Mobile
//
//  Created by Kuznetsov Mikhail on 17.01.2025.
//

import AppBase

final class CartProductDelegateMock: ICartProductDelegate {

    var invokedCartProductDidChanged = false
    var invokedCartProductDidChangedCount = 0

    func cartProductDidChanged() {
        invokedCartProductDidChanged = true
        invokedCartProductDidChangedCount += 1
    }
}
