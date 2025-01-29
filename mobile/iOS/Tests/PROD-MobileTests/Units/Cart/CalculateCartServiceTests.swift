//
//  CalculateCartServiceTests.swift
//  PROD-MobileTests
//
//  Created by Arina Kolganova on 12.01.2025.
//

import XCTest
import AppBase
@testable import Solution

final class CalculateCartServiceTests: XCTestCase {

    // SUT
    private var sut: ICalculateCartService!

    // MARK: - XCTestCase

    override func setUp() {
        super.setUp()
        sut = SolutionAssembly.calculateCartService()
    }

    override func tearDown() {
        sut = nil
        super.tearDown()
    }

    // MARK: - Tests

    func test_calculateCash_withEmptyObject_score_1() {
        // Given
        let product: [CartProduct] = []

        // When
        let cash = sut.calculateCash(for: product)

        // Then
        XCTAssertEqual(cash, .zero)
    }

    func test_calculateCash_singleObject_countIsZero_score_1() {
        // Given
        let product = [CartProduct(product: Product.mock(cost: 100.0), count: .zero)]

        // When
        let cash = sut.calculateCash(for: product)

        // Then
        XCTAssertEqual(cash, .zero)
    }

    func test_calculateCash_singleObject_costIsZero_score_1() {
        // Given
        let product = [CartProduct(product: Product.mock(cost: 0.0), count: 100)]

        // When
        let cash = sut.calculateCash(for: product)

        // Then
        XCTAssertEqual(cash, .zero)
    }

    func test_calculateCash_multipleObjects_score_1() {
        // Given
        let products = [
            CartProduct(product: Product.mock(cost: 100.0), count: 2),
            CartProduct(product: Product.mock(cost: 50.0), count: 3)
        ]

        // When
        let cash = sut.calculateCash(for: products)

        // Then
        XCTAssertEqual(cash, 350.0)
    }

    func test_calculateWeight_withEmptyObject_score_1() {
        // Given
        let product: [CartProduct] = []

        // When
        let weight = sut.calculateWeight(for: product)

        // Then
        XCTAssertEqual(weight, .zero)
    }

    func test_calculateWeight_singleObject_countIsZero_score_1() {
        // Given
        let product = Product.mock(itemCountity: .init(type: .kilo, value: 2))
        let products = [CartProduct(product: product, count: 0)]

        // When
        let weight = sut.calculateWeight(for: products)

        // Then
        XCTAssertEqual(weight, .zero)
    }

    func test_calculateWeight_singleObject_typeIsKilo_score_1() {
        // Given
        let product = Product.mock(itemCountity: .init(type: .kilo, value: 2))
        let products = [CartProduct(product: product, count: 1)]

        // When
        let weight = sut.calculateWeight(for: products)

        // Then
        XCTAssertEqual(weight, 2000)
    }

    func test_calculateWeight_singleObject_typeIsGramm_score_1() {
        // Given
        let product = Product.mock(itemCountity: .init(type: .gramm, value: 500))
        let products = [CartProduct(product: product, count: 2)]

        // When
        let weight = sut.calculateWeight(for: products)

        // Then
        XCTAssertEqual(weight, 1000)
    }

    func test_calculateWeight_multipleObjects_score_1() {
        // Given
        let products = [
            CartProduct(product: Product.mock(itemCountity: .init(type: .kilo, value: 1)), count: 2),
            CartProduct(product: Product.mock(itemCountity: .init(type: .gramm, value: 500)), count: 3)
        ]

        // When
        let weight = sut.calculateWeight(for: products)

        // Then
        XCTAssertEqual(weight, 3500)
    }

    func test_calculateBonus_withEmptyProducts_score_1() {
        // Given
        let bonuses: [Bonus] = []
        let userInfo = UserInfo.mock()

        // When
        let (cashback, bonusPoints) = sut.calculateBonus(for: [], bonuses: bonuses, userInfo: userInfo)

        // Then
        XCTAssertEqual(cashback, .zero)
        XCTAssertEqual(bonusPoints, .zero)
    }

    func test_calculateBonus_withEmptyBonuses_score_1() {
        // Given
        let product = Product.mock(cost: 200.0, bonusIds: ["999"])
        let userInfo = UserInfo.mock(activityLevel: 50)

        // When
        let (cashback, bonusPoints) = sut.calculateBonus(for: [CartProduct(product: product, count: 1)], bonuses: [], userInfo: userInfo)

        // Then
        XCTAssertEqual(cashback, 4.0)
        XCTAssertEqual(bonusPoints, .zero)
    }

    func test_calculateBonus_withValidCashback_score_1() {
        // Given
        let bonus = Bonus.mock(id: "101", type: .cashback, value: 0.1)
        let product = Product.mock(cost: 100.0, bonusIds: ["101"])
        let userInfo = UserInfo.mock()

        // When
        let (cashback, bonusPoints) = sut.calculateBonus(for: [CartProduct(product: product, count: 2)], bonuses: [bonus], userInfo: userInfo)

        // Then
        XCTAssertEqual(cashback, 20.0)
        XCTAssertEqual(bonusPoints, .zero)
    }

    func test_calculateBonus_withValidPoints_score_1() {
        // Given
        let bonus = Bonus.mock(id: "102", type: .points, value: 50.0)
        let product = Product.mock(cost: 100.0, bonusIds: ["102"])
        let userInfo = UserInfo.mock()

        // When
        let (cashback, bonusPoints) = sut.calculateBonus(for: [CartProduct(product: product, count: 2)], bonuses: [bonus], userInfo: userInfo)

        // Then
        XCTAssertEqual(cashback, .zero)
        XCTAssertEqual(bonusPoints, 100.0)
    }

    func test_calculateBonus_withFavouritePointsBonus_score_1() {
        // Given
        let bonus = Bonus.mock(id: "102", type: .points, value: 50.0)
        let product = Product.mock(cost: 100.0, bonusIds: ["102"])
        let userInfo = UserInfo.mock(favourites: [product.id])

        // When
        let (cashback, bonusPoints) = sut.calculateBonus(for: [CartProduct(product: product, count: 2)], bonuses: [bonus], userInfo: userInfo)

        // Then
        XCTAssertEqual(cashback, .zero)
        XCTAssertEqual(bonusPoints, 120.0)
    }

    func test_calculateBonus_withNullActivityLevel_score_1() {
        // Given
        let expiredBonus = Bonus.mock(id: "103", type: .cashback, value: 0.1, availableDueTo: "2024-04-23T18:25:43.511Z")
        let product = Product.mock(cost: 100.0, bonusIds: ["103"])
        let userInfo = UserInfo.mock(activityLevel: 0)

        // When
        let (cashback, bonusPoints) = sut.calculateBonus(for: [CartProduct(product: product, count: 3)], bonuses: [expiredBonus], userInfo: userInfo)

        // Then
        XCTAssertEqual(cashback, .zero)
        XCTAssertEqual(bonusPoints, .zero)
    }

    func test_calculateBonus_withLowActivityLevel_score_1() {
        // Given
        let expiredBonus = Bonus.mock(id: "103", type: .cashback, value: 0.1, availableDueTo: "2024-04-23T18:25:43.511Z")
        let product = Product.mock(cost: 100.0, bonusIds: ["103"])
        let userInfo = UserInfo.mock(activityLevel: 25)

        // When
        let (cashback, bonusPoints) = sut.calculateBonus(for: [CartProduct(product: product, count: 3)], bonuses: [expiredBonus], userInfo: userInfo)

        // Then
        XCTAssertEqual(cashback, .zero)
        XCTAssertEqual(bonusPoints, .zero)
    }

    func test_calculateBonus_withMediumActivityLevel_score_1() {
        // Given
        let expiredBonus = Bonus.mock(id: "103", type: .cashback, value: 0.1, availableDueTo: "2024-04-23T18:25:43.511Z")
        let product = Product.mock(cost: 100.0, bonusIds: ["103"])
        let userInfo = UserInfo.mock(activityLevel: 50)

        // When
        let (cashback, bonusPoints) = sut.calculateBonus(for: [CartProduct(product: product, count: 3)], bonuses: [expiredBonus], userInfo: userInfo)

        // Then
        XCTAssertEqual(cashback, 6.0)
        XCTAssertEqual(bonusPoints, .zero)
    }

    func test_calculateBonus_withHightActivityLevel_score_1() {
        // Given
        let expiredBonus = Bonus.mock(id: "103", type: .cashback, value: 0.1, availableDueTo: "2024-04-23T18:25:43.511Z")
        let product = Product.mock(cost: 100.0, bonusIds: ["103"])
        let userInfo = UserInfo.mock(activityLevel: 75)

        // When
        let (cashback, bonusPoints) = sut.calculateBonus(for: [CartProduct(product: product, count: 3)], bonuses: [expiredBonus], userInfo: userInfo)

        // Then
        XCTAssertEqual(cashback, 9.0)
        XCTAssertEqual(bonusPoints, .zero)
    }

    func test_calculateBonus_withVeryHightActivityLevel_score_1() {
        // Given
        let expiredBonus = Bonus.mock(id: "103", type: .cashback, value: 0.1, availableDueTo: "2024-04-23T18:25:43.511Z")
        let product = Product.mock(cost: 100.0, bonusIds: ["103"])
        let userInfo = UserInfo.mock(activityLevel: 100)

        // When
        let (cashback, bonusPoints) = sut.calculateBonus(for: [CartProduct(product: product, count: 3)], bonuses: [expiredBonus], userInfo: userInfo)

        // Then
        XCTAssertEqual(cashback, 15.0)
        XCTAssertEqual(bonusPoints, .zero)
    }

    func test_calculateBonus_withLastAvailableDueTo_score_1() {
        // Given
        let expiredBonus = Bonus.mock(id: "103", type: .cashback, value: 0.1, availableDueTo: "2024-04-23T18:25:43.511Z")
        let product = Product.mock(cost: 100.0, bonusIds: ["103"])
        let userInfo = UserInfo.mock(activityLevel: 75)

        // When
        let (cashback, bonusPoints) = sut.calculateBonus(for: [CartProduct(product: product, count: 3)], bonuses: [expiredBonus], userInfo: userInfo)

        // Then
        XCTAssertEqual(cashback, 9.0)
        XCTAssertEqual(bonusPoints, .zero)
    }

    func test_calculateBonus_withValidAvailableDueTo_score_1() {
        // Given
        let expiredBonus = Bonus.mock(id: "103", type: .cashback, value: 0.1, availableDueTo: "2025-04-23T18:25:43.511Z")
        let product = Product.mock(cost: 100.0, bonusIds: ["103"])
        let userInfo = UserInfo.mock(activityLevel: 75)

        // When
        let (cashback, bonusPoints) = sut.calculateBonus(for: [CartProduct(product: product, count: 3)], bonuses: [expiredBonus], userInfo: userInfo)

        // Then
        XCTAssertEqual(cashback, 30.0)
        XCTAssertEqual(bonusPoints, .zero)
    }

    func test_calculateBonus_withMultipleBonuses_score_1() {
        // Given
        let bonuses: [Bonus] = [
            Bonus.mock(id: "101", type: .cashback, value: 0.05),
            Bonus.mock(id: "102", type: .points, value: 10.0),
            Bonus.mock(id: "103", type: .cashback, value: 0.05)
        ]
        let firstProduct = Product.mock(cost: 100.0, bonusIds: ["101", "102"])
        let secondProduct = Product.mock(cost: 2500.0, bonusIds: ["103"])
        let thirdProduct = Product.mock(cost: 100000.0, bonusIds: [])
        let userInfo = UserInfo.mock()

        // When
        let (cashback, bonusPoints) = sut.calculateBonus(for: [CartProduct(product: firstProduct, count: 2), CartProduct(product: secondProduct, count: 1), CartProduct(product: thirdProduct, count: 10)], bonuses: bonuses, userInfo: userInfo)

        // Then
        XCTAssertEqual(cashback, 135.0)
        XCTAssertEqual(bonusPoints, 20.0)
    }
}

