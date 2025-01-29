//
//  ProductViewUnitTests.swift
//  PROD-Mobile
//
//  Created by Kuznetsov Mikhail on 16.01.2025.
//

import Foundation
import XCTest
import AppBase
import Solution

final class ProductViewUnitTests: XCTestCase {
    
    private var sut: IProductView!
    private var cartServiceMock: CartServiceMock!
    
    override func setUp() {
        cartServiceMock = CartServiceMock()
        
        sut = SolutionAssembly.productViewFactory()
            .makeProductView(cartService: cartServiceMock)
    }
    
    override func tearDown() {
        sut = nil
        
        cartServiceMock = nil
    }
    
    func test_subscribe_score_1() {
        XCTAssertEqual(cartServiceMock.invokedSubscribeCount, 1)
        XCTAssertIdentical(cartServiceMock.invokedSubscribeParameters?.delegate, sut)
    }
    
    func test_update_score_2() {
        
        let buttonsInteractionModel = ButtonsViewInteractionModelMock()
        
        sut.configure(product: .mock(), buttonsInteractionModel: buttonsInteractionModel)
        
        buttonsInteractionModel.invokedQuantityGetter = false
        sut.cartProductDidChanged()
        
        XCTAssertTrue(buttonsInteractionModel.invokedQuantityGetter)
    }
    
}
