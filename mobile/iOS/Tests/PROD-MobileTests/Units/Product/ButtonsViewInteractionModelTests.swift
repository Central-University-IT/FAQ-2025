//
//  ButtonsViewInteractionModelTests.swift
//  PROD-Mobile
//
//  Created by Kuznetsov Mikhail on 16.01.2025.
//

import Foundation
import XCTest
import AppBase
import Solution

final class ButtonsViewInteractionModelTests: XCTestCase {
    
    private var sut: IButtonsViewInteractionModel!
    private var cartServiceMock: CartServiceMock!
    
    override func setUp() {
        cartServiceMock = CartServiceMock()
        
        sut = SolutionAssembly.buttonsViewInteractionModel(
            productId: "item_123",
            cartService: cartServiceMock
        )
    }
    
    override func tearDown() {
        sut = nil
        
        cartServiceMock = nil
    }
    
    func test_quantity_score_4() {
        cartServiceMock.stubbedProductCountResult = 1
        
        XCTAssertEqual(1, sut.quantity)
        XCTAssertEqual(1, cartServiceMock.invokedProductCountCount)
        XCTAssertEqual("item_123", cartServiceMock.invokedProductCountParameters?.id)
        
        cartServiceMock.stubbedProductCountResult = 2
        
        XCTAssertEqual(2, sut.quantity)
        XCTAssertEqual(2, cartServiceMock.invokedProductCountCount)
        XCTAssertEqual("item_123", cartServiceMock.invokedProductCountParameters?.id)
    }
    
    func test_add_score_5() {
        cartServiceMock.stubbedProductCountResult = 1
        
        sut.onPressedAdd()
        
        XCTAssertEqual(1, cartServiceMock.invokedUpdateProductCount)
        XCTAssertEqual(1, cartServiceMock.invokedProductCountCount)
        XCTAssertEqual("item_123", cartServiceMock.invokedUpdateProductParameters?.id)
        XCTAssertEqual(2, cartServiceMock.invokedUpdateProductParameters?.count)
    }
    
    func test_remove_score_5() {
        cartServiceMock.stubbedProductCountResult = 3
        
        sut.onPressedRemove()
        
        XCTAssertEqual(1, cartServiceMock.invokedUpdateProductCount)
        XCTAssertEqual(1, cartServiceMock.invokedProductCountCount)
        XCTAssertEqual("item_123", cartServiceMock.invokedUpdateProductParameters?.id)
        XCTAssertEqual(2, cartServiceMock.invokedUpdateProductParameters?.count)
    }
}
    
