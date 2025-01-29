//
//  CartProductViewModelFactoryTests.swift
//  PROD-MobileTests
//
//  Created by Arina Kolganova on 13.01.2025.
//

import Foundation
import XCTest
import AppBase
@testable import Solution

final class CartProductViewModelFactoryTests: XCTestCase {

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

    func test_makeCartViewModel_emptyProduct_score_1() {
        // Given
        let product = Product.mock()
        let count = 10

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertEqual(viewModel.id, product.id)
        XCTAssertEqual(viewModel.name, product.name)
        XCTAssertEqual(viewModel.totalWeight, "0 кг")
        XCTAssertEqual(viewModel.totalPrice, "0 ₽")
        XCTAssertEqual(viewModel.priceDescription, "10 шт. (по 0кг)* 0 ₽")
        XCTAssertEqual(viewModel.image, imageFactoryMock.goodsItemImage(id: product.imageId))
    }

    func test_makeCartViewModel_zeroCount_score_1() {
        // Given
        let product = Product.mock(cost: 100.0, itemCountity: Product.ItemCountity(type: .gramm, value: 500.0))
        let count = 0

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertEqual(viewModel.id, product.id)
        XCTAssertEqual(viewModel.name, product.name)
        XCTAssertEqual(viewModel.totalWeight, "0 г")
        XCTAssertEqual(viewModel.totalPrice, "0 ₽")
        XCTAssertEqual(viewModel.priceDescription, "0 шт. (по 500г)* 100 ₽")
        XCTAssertEqual(viewModel.image, imageFactoryMock.goodsItemImage(id: product.imageId))
    }

    func test_makeCartViewModel_cost_singleProductCostWithFractional_score_1() {
        // Given
        let product = Product.mock(cost: 0.250)
        let count = 1

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertTrue(["0,25 ₽", "0.25 ₽"].contains(viewModel.totalPrice))
    }

    func test_makeCartViewModel_cost_singleProduct_score_1() {
        // Given
        let product = Product.mock(cost: 5.0)
        let count = 1

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertEqual(viewModel.totalPrice, "5 ₽")
    }

    func test_makeCartViewModel_cost_costWithFractional_score_1() {
        // Given
        let product = Product.mock(cost: 0.250)
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertEqual(viewModel.totalPrice, "0,5 ₽")
    }

    func test_makeCartViewModel_cost_score_1() {
        // Given
        let product = Product.mock(cost: 5.0)
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertEqual(viewModel.totalPrice, "10 ₽")
    }

    func test_makeCartViewModel_weight_gramsMoreThan1000_score_1() {
        // Given
        let product = Product.mock(itemCountity: Product.ItemCountity(type:.gramm, value: 1000))
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertEqual(viewModel.totalWeight, "2 кг")
    }

    func test_makeCartViewModel_weight_gramsMoreThan1000WithFractional_score_1() {
        // Given
        let product = Product.mock(itemCountity: Product.ItemCountity(type:.gramm, value: 1250))
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertTrue(["2,5 кг", "2.5 кг"].contains(viewModel.totalWeight))
    }

    func test_makeCartViewModel_weight_gramsLessThan1000WithFractional_score_1() {
        // Given
        let product = Product.mock(itemCountity: Product.ItemCountity(type:.gramm, value: 250))
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertEqual(viewModel.totalWeight, "500 г")
    }

    func test_makeCartViewModel_weight_kilo_score_1() {
        // Given
        let product = Product.mock(itemCountity: Product.ItemCountity(type:.kilo, value: 2))
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertEqual(viewModel.totalWeight, "4 кг")
    }

    func test_makeCartViewModel_weight_kiloWithFractional_score_1() {
        // Given
        let product = Product.mock(itemCountity: Product.ItemCountity(type:.kilo, value: 2.25))
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertTrue(["4,5 кг", "4.5 кг"].contains(viewModel.totalWeight))
    }

    func test_makeCartViewModel_description_singleProductWithGrams_score_1() {
        // Given
        let product = Product.mock(cost: 100.0, itemCountity: Product.ItemCountity(type: .gramm, value: 500.0))
        let count = 1

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertEqual(viewModel.priceDescription, "1 шт. (по 500г)* 100 ₽")
    }

    func test_makeCartViewModel_description_singleProductWithGramsWithFractional_score_1() {
        // Given
        let product = Product.mock(cost: 100.0, itemCountity: Product.ItemCountity(type: .gramm, value: 500.5))
        let count = 1

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertTrue(["1 шт. (по 500,5г)* 100 ₽", "1 шт. (по 500.5г)* 100 ₽"].contains(viewModel.priceDescription))
    }

    func test_makeCartViewModel_description_singleProductWithKilo_score_1() {
        // Given
        let product = Product.mock(cost: 100.0, itemCountity: Product.ItemCountity(type: .kilo, value: 50.0))
        let count = 1

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertEqual(viewModel.priceDescription, "1 шт. (по 50кг)* 100 ₽")
    }

    func test_makeCartViewModel_description_singleProductWithKiloWithFractional_score_1() {
        // Given
        let product = Product.mock(cost: 100.0, itemCountity: Product.ItemCountity(type: .kilo, value: 50.50))
        let count = 1

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertTrue(["1 шт. (по 50,5кг)* 100 ₽", "1 шт. (по 50.5кг)* 100 ₽"].contains(viewModel.priceDescription))
    }

    func test_makeCartViewModel_description_singleProductCostWithFractional_score_1() {
        // Given
        let product = Product.mock(cost: 100.25, itemCountity: Product.ItemCountity(type: .kilo, value: 50.0))
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertTrue(["2 шт. (по 50кг)* 100,25 ₽", "2 шт. (по 50кг)* 100.25 ₽"].contains(viewModel.priceDescription))
    }

    func test_makeCartViewModel_description_costWithFractional_score_1() {
        // Given
        let product = Product.mock(cost: 100.50, itemCountity: Product.ItemCountity(type: .kilo, value: 50.0))
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertTrue(["2 шт. (по 50кг)* 100,5 ₽", "2 шт. (по 50кг)* 100.5 ₽"].contains(viewModel.priceDescription))
    }

    func test_makeCartViewModel_description_withGrams_score_1() {
        // Given
        let product = Product.mock(cost: 100.0, itemCountity: Product.ItemCountity(type: .gramm, value: 500.0))
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertEqual(viewModel.priceDescription, "2 шт. (по 500г)* 100 ₽")
    }

    func test_makeCartViewModel_description_withFractional_score_1() {
        // Given
        let product = Product.mock(cost: 100.0, itemCountity: Product.ItemCountity(type: .gramm, value: 500.5))
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertTrue(["2 шт. (по 500,5г)* 100 ₽", "2 шт. (по 500.5г)* 100 ₽"].contains(viewModel.priceDescription))
    }

    func test_makeCartViewModel_description_withKilo_score_1() {
        // Given
        let product = Product.mock(cost: 100.0, itemCountity: Product.ItemCountity(type: .kilo, value: 50.0))
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertEqual(viewModel.priceDescription, "2 шт. (по 50кг)* 100 ₽")
    }

    func test_makeCartViewModel_description_withKiloWithFractional_score_1() {
        // Given
        let product = Product.mock(cost: 100.0, itemCountity: Product.ItemCountity(type: .kilo, value: 50.50))
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertTrue(["2 шт. (по 50,5кг)* 100 ₽", "2 шт. (по 50.5кг)* 100 ₽"].contains(viewModel.priceDescription))
    }

    func test_makeCartViewModel_validProduct_score_1() {
        // Given
        let product = Product.mock(cost: 100.0, itemCountity: Product.ItemCountity(type: .gramm, value: 500.0))
        let count = 2

        // When
        let viewModel = sut.makeCartViewModel(product: product, count: count)

        // Then
        XCTAssertEqual(viewModel.id, product.id)
        XCTAssertEqual(viewModel.name, product.name)
        XCTAssertEqual(viewModel.totalWeight, "1 000 г")
        XCTAssertEqual(viewModel.totalPrice, "200 ₽")
        XCTAssertEqual(viewModel.priceDescription, "2 шт. (по 500г)* 100 ₽")
        XCTAssertEqual(viewModel.image, imageFactoryMock.goodsItemImage(id: product.imageId))
    }
}
