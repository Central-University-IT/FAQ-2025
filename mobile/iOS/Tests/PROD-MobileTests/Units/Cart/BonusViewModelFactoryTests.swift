//
//  BonusViewModelFactoryTests.swift
//  PROD-MobileTests
//
//  Created by Arina Kolganova on 12.01.2025.
//

import Foundation
import XCTest
import AppBase
@testable import Solution

final class BonusViewModelFactoryTests: XCTestCase {

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

    func test_makeBonusViewModel_zeroCashbackAndBonus_score_1() {
        // Given
        let cashback = 0.0
        let bonus = 0.0

        // When
        let viewModel = sut.makeBonusViewModel(cashback: cashback, bonus: bonus)

        // Then
        XCTAssertEqual(viewModel.cashback, "")
        XCTAssertEqual(viewModel.bonus, "")
    }

    func test_makeBonusViewModel_smallFractionalBonus_score_1() {
        // Given
        let cashback = 0.0
        let bonus = 0.75

        // When
        let viewModel = sut.makeBonusViewModel(cashback: cashback, bonus: bonus)

        // Then
        XCTAssertEqual(viewModel.cashback, "")
        XCTAssertTrue(["0,75 баллов", "0.75 баллов"].contains(viewModel.bonus))
    }

    func test_makeBonusViewModel_smallFractionalCashback_score_1() {
        // Given
        let cashback = 0.75
        let bonus = 0.0

        // When
        let viewModel = sut.makeBonusViewModel(cashback: cashback, bonus: bonus)

        // Then
        XCTAssertTrue(["0,75 ₽", "0.75 ₽"].contains(viewModel.cashback))
        XCTAssertEqual(viewModel.bonus, "")
    }

    func test_makeBonusViewModel_validData_score_1() {
        // Given
        let cashback = 1234.56
        let bonus = 567.89

        // When
        let viewModel = sut.makeBonusViewModel(cashback: cashback, bonus: bonus)

        // Then
        XCTAssertTrue(["1 234,56 ₽", "1 234.56 ₽"].contains(viewModel.cashback))
        XCTAssertTrue(["567,89 баллов", "567.89 баллов"].contains(viewModel.bonus))
    }
}
