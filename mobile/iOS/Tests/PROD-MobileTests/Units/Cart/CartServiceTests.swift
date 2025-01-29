//
//  CartServiceTests.swift
//  PROD-Mobile
//
//  Created by Kuznetsov Mikhail on 17.01.2025.
//

import XCTest
import AppBase
@testable import Solution

final class CartServiceTests: XCTestCase {
    
    // SUT
    private var sut: ICartService!
    
    // Mock
    private var storage: PersistenceStorageMock!
    
    // MARK: - XCTestCase
    
    override func setUp() {
        super.setUp()
        storage = PersistenceStorageMock()
        sut = SolutionAssembly.cartService(storage: storage)
    }
    
    override func tearDown() {
        sut = nil
        storage = nil
        super.tearDown()
    }
    
    func test_updateProduct_score_4() throws {
        let expectation = expectation(description: #function)
        sut.updateProduct(id: "id_123", count: 10)
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5, execute: expectation.fulfill)
        wait(for: [expectation], timeout: 0.6)
        XCTAssertTrue(storage.invokedSave)
        let item = try XCTUnwrap(storage.invokedSaveParameters?.item as? ProductCartModel)
        
        XCTAssertEqual(item.identifier, "id_123")
        XCTAssertEqual(item.quantity, 10)
    }
    
    func test_removeProduct_score_4() throws {
        let expectation = expectation(description: #function)
        sut.removeProduct(id: "id_123")
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5, execute: expectation.fulfill)
        wait(for: [expectation], timeout: 0.6)
        XCTAssertTrue(storage.invokedDelete)
        let item = try XCTUnwrap(storage.invokedDeleteParameters?.item as? ProductCartModel)
        
        XCTAssertEqual(item.identifier, "id_123")
    }
    
    func test_loadCartProduct_score_5() {
        storage.stubbedFetchResultList = [
            [ProductCartModel(identifier: "id_234", quantity: 2), ProductCartModel(identifier: "id_123", quantity: 10)],
            [Product.mock(id: "asd"), Product.mock(id: "id_234"), Product.mock(id: "id_123")]
        ]
        
        let expectation = expectation(description: #function)
        sut.updateProduct(id: "id_123", count: 10)
        sut.updateProduct(id: "id_234", count: 2)
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5, execute: expectation.fulfill)
        wait(for: [expectation], timeout: 0.6)
        
        let cart = sut.loadCartProduct()
        
        XCTAssertEqual(cart.count, 2)
        XCTAssertEqual(cart[0].product.id, "id_123")
        XCTAssertEqual(cart[0].count, 10)
        XCTAssertEqual(cart[1].product.id, "id_234")
        XCTAssertEqual(cart[1].count, 2)
    }
    
    func test_productCount_some_score_4() {
        storage.stubbedFetchResult = [
            ProductCartModel(identifier: "123", quantity: 1),
            ProductCartModel(identifier: "2344", quantity: 2),
            ProductCartModel(identifier: "432", quantity: 3),
        ]
        
        let quantity = sut.productCount(id: "2344")
        
        XCTAssertEqual(quantity, 2)
        XCTAssertTrue(storage.invokedFetch)
    }
    
    func test_productCount_zero_score_4() {
        storage.stubbedFetchResult = [
            ProductCartModel(identifier: "123", quantity: 1),
            ProductCartModel(identifier: "2344", quantity: 2),
            ProductCartModel(identifier: "432", quantity: 3),
        ]
        
        let quantity = sut.productCount(id: "9878")
        
        XCTAssertEqual(quantity, 0)
        XCTAssertTrue(storage.invokedFetch)
    }
    
    func test_subscribe_score_4() {
        let delegate1 = CartProductDelegateMock()
        let delegate2 = CartProductDelegateMock()
        
        sut.subscribe(delegate1)
        sut.subscribe(delegate2)
        
        let expectation = expectation(description: #function)
        sut.updateProduct(id: "id_123", count: 10)
        sut.removeProduct(id: "id_123")
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5, execute: expectation.fulfill)
        wait(for: [expectation], timeout: 0.6)
        
        XCTAssertEqual(delegate1.invokedCartProductDidChangedCount, 2)
        XCTAssertEqual(delegate2.invokedCartProductDidChangedCount, 2)
        
    }
    
    func test_productSequence_update_score_4() {
        let expectation = expectation(description: #function)
        sut.updateProduct(id: "id_123", count: 10)
        sut.updateProduct(id: "id_234", count: 2)
        sut.updateProduct(id: "id_123", count: 2)
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5, execute: expectation.fulfill)
        wait(for: [expectation], timeout: 0.6)
        
        XCTAssertEqual(sut.productSequence[0], "id_123")
        XCTAssertEqual(sut.productSequence[1], "id_234")
    }
    
    func test_productSequence_remove_score_4() {
        let expectation = expectation(description: #function)
        sut.updateProduct(id: "id_123", count: 10)
        sut.updateProduct(id: "id_234", count: 2)
        sut.removeProduct(id: "id_123")
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5, execute: expectation.fulfill)
        wait(for: [expectation], timeout: 0.6)
        
        XCTAssertEqual(sut.productSequence[0], "id_234")
    }
}


