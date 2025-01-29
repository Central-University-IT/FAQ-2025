//
//  CartServiceMock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 11.01.2025.
//

import AppBase

final class CartServiceMock: ICartService {

    var invokedProductSequenceGetter = false
    var invokedProductSequenceGetterCount = 0
    var stubbedProductSequence: [String]! = []

    var productSequence: [String] {
        invokedProductSequenceGetter = true
        invokedProductSequenceGetterCount += 1
        return stubbedProductSequence
    }

    var invokedUpdateProduct = false
    var invokedUpdateProductCount = 0
    var invokedUpdateProductParameters: (id: String, count: Int)?
    var invokedUpdateProductParametersList = [(id: String, count: Int)]()

    func updateProduct(id: String, count: Int) {
        invokedUpdateProduct = true
        invokedUpdateProductCount += 1
        invokedUpdateProductParameters = (id, count)
        invokedUpdateProductParametersList.append((id, count))
    }

    var invokedRemoveProduct = false
    var invokedRemoveProductCount = 0
    var invokedRemoveProductParameters: (id: String, Void)?
    var invokedRemoveProductParametersList = [(id: String, Void)]()

    func removeProduct(id: String) {
        invokedRemoveProduct = true
        invokedRemoveProductCount += 1
        invokedRemoveProductParameters = (id, ())
        invokedRemoveProductParametersList.append((id, ()))
    }

    var invokedLoadCartProduct = false
    var invokedLoadCartProductCount = 0
    var stubbedLoadCartProductResult: [CartProduct]! = []

    func loadCartProduct() -> [CartProduct] {
        invokedLoadCartProduct = true
        invokedLoadCartProductCount += 1
        return stubbedLoadCartProductResult
    }

    var invokedProductCount = false
    var invokedProductCountCount = 0
    var invokedProductCountParameters: (id: String, Void)?
    var invokedProductCountParametersList = [(id: String, Void)]()
    var stubbedProductCountResult: Int! = 0

    func productCount(id: String) -> Int {
        invokedProductCount = true
        invokedProductCountCount += 1
        invokedProductCountParameters = (id, ())
        invokedProductCountParametersList.append((id, ()))
        return stubbedProductCountResult
    }

    var invokedSubscribe = false
    var invokedSubscribeCount = 0
    var invokedSubscribeParameters: (delegate: ICartProductDelegate, Void)?
    var invokedSubscribeParametersList = [(delegate: ICartProductDelegate, Void)]()

    func subscribe(_ delegate: ICartProductDelegate) {
        invokedSubscribe = true
        invokedSubscribeCount += 1
        invokedSubscribeParameters = (delegate, ())
        invokedSubscribeParametersList.append((delegate, ()))
    }
}
