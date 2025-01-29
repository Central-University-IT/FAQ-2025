//
//  ProductViewFactoryMock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 07.01.2025.
//

import AppBase

final class ProductViewFactoryMock: IProductViewFactory {
 
    var invokedMakeProductView = false
    var invokedMakeProductViewCount = 0
    var invokedConfigureParameters: (cartService: ICartService, Void)?
    var invokedConfigureParametersList = [(cartService: ICartService, Void)]()
    var stubbedMakeProductViewResult = ProductViewMock()
    
    func makeProductView(cartService: ICartService) -> IProductView {
        invokedMakeProductView = true
        invokedMakeProductViewCount += 1
        invokedConfigureParameters = (cartService, ())
        invokedConfigureParametersList.append((cartService, ()))
        return stubbedMakeProductViewResult
    }
}
