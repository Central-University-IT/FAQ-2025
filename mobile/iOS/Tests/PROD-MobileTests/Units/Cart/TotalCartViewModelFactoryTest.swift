//
//  TotalCartViewModelFactoryTest.swift
//  PROD-MobileTests
//
//  Created by Arina Kolganova on 13.01.2025.
//

import Foundation
import XCTest
import AppBase
@testable import Solution

final class TotalCartViewModelFactoryTest: XCTestCase {

    // SUT
    private var sut: IProductViewModelFactory!

    // Mocks
    private var imageFactoryMock: ImageFactoryMock!

    // MARK: - XCTestCase

    override func setUp() {
        imageFactoryMock = ImageFactoryMock()
        sut = SolutionAssembly.productViewModelFactory(imageFactory: imageFactoryMock)
    }

    override func tearDown() {
        imageFactoryMock = nil
        sut = nil
    }

    // MARK: - Tests

    func test_makeTotalCartViewModel_zeroPriceAndWeight_score_1() {
        let price = 0.0
        let weight = 0.0

        let viewModel = sut.makeTotalCartViewModel(price: price, weight: weight)

        XCTAssertTrue(["", "0.0 ₽", "0,0 ₽"].contains(viewModel.price))
        XCTAssertTrue(["", "0.0 г", "0,0 г"].contains(viewModel.weight))

    }

    func test_makeTotalCartViewModel_validData_score_1() {
        let price = 1500.0
        let weight = 2500.0

        let viewModel = sut.makeTotalCartViewModel(price: price, weight: weight)

        XCTAssertEqual(viewModel.price, "1 500 ₽")
        XCTAssertTrue(["2,5 кг", "2.5 кг"].contains(viewModel.weight))
    }

    func test_makeTotalCartViewModel_fractionalWeight_score_1() {
        let price = 1234.56
        let weight = 1500.5

        let viewModel = sut.makeTotalCartViewModel(price: price, weight: weight)

        XCTAssertTrue(["1 234,56 ₽", "1 234.56 ₽"].contains(viewModel.price))
        XCTAssertTrue(["1,5 кг", "1.5 кг"].contains(viewModel.weight))
    }

    func test_makeTotalCartViewModel_smallFractionalPrice_score_1() {
        let price = 0.75
        let weight = 0.0

        let viewModel = sut.makeTotalCartViewModel(price: price, weight: weight)

        XCTAssertTrue(["0,75 ₽", "0.75 ₽"].contains(viewModel.price))
        XCTAssertTrue(["", "0.0 г", "0,0 г"].contains(viewModel.weight))
    }

    func test_makeTotalCartViewModel_smallFractionalWeight_score_1() {
        let price = 0.0
        let weight = 1500.5

        let viewModel = sut.makeTotalCartViewModel(price: price, weight: weight)

        XCTAssertTrue(["", "0.0 ₽", "0,0 ₽"].contains(viewModel.price))
        XCTAssertTrue(["1,5 кг", "1.5 кг"].contains(viewModel.weight))
    }
}
