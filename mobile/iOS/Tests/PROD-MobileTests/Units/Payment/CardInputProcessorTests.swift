//
//  CardInputProcessorTests.swift
//  PROD-MobileTests
//
//  Created by m.titor on 07.01.2025.
//

import XCTest
import AppBase
@testable import Solution

final class CardInputProcessorTests: XCTestCase {
    
    // SUT
    private var sut: ICardInputProcessor!
    
    // MARK: - XCTestCase
    
    override func setUp() {
        super.setUp()
        sut = SolutionAssembly.cardInputProcessor()
    }
    
    override func tearDown() {
        sut = nil
        super.tearDown()
    }
    
    // MARK: - Tests
    
    func test_validateCardNumber_withEmptyString_shouldBeCorrect_score_1() {
        // Given
        var field: CardInputViewModel.Field = .correct("")
        
        // When
        sut.validateCardNumber(&field)
        
        // Then
        XCTAssertTrue(field == .correct(""))
    }
    
    func test_validateCardNumber_withValid16Digits_shouldBeCorrect_score_1() {
        // Given
        var field: CardInputViewModel.Field = .correct("1234 5678 1234 5678")
        
        // When
        sut.validateCardNumber(&field)
        
        // Then
        XCTAssertTrue(field == .correct("1234 5678 1234 5678"))
    }
    
    func test_validateCardNumber_withNonNumericCharacters_shouldBeIncorrect_score_1() {
        // Given
        var field: CardInputViewModel.Field = .incorrect("1234 5678 1234 ABCD")
        
        // When
        sut.validateCardNumber(&field)
        
        // Then
        XCTAssertTrue(field == .incorrect("1234 5678 1234 ABCD"))
    }
    
    func test_validateCardNumber_withMoreThan16Digits_shouldBeIncorrect_score_1() {
        // Given
        var field: CardInputViewModel.Field = .incorrect("1234 5678 1234 5678 1234")
        
        // When
        sut.validateCardNumber(&field)
        
        // Then
        XCTAssertTrue(field == .incorrect("1234 5678 1234 5678 1234"))
    }
    
    func test_validateCardNumber_withValidDigitsNoSpaces_shouldBeCorrect_score_1() {
        // Given
        var field: CardInputViewModel.Field = .correct("1234567812345678")
        
        // When
        sut.validateCardNumber(&field)
        
        // Then
        XCTAssertTrue(field == .correct("1234567812345678"))
    }
    
    func test_validateCardNumber_withLessThan16Digits_shouldBeCorrect_score_1() {
        // Given
        var field: CardInputViewModel.Field = .correct("1234 5678 1234")
        
        // When
        sut.validateCardNumber(&field)
        
        // Then
        XCTAssertTrue(field == .correct("1234 5678 1234"))
    }
    
    func test_validateExpiryDate_withEmptyString_shouldBeCorrect_score_1() {
        // Given
        var field = CardInputViewModel.Field.correct("")
        
        // When
        sut.validateExpiryDate(&field)
        
        // Then
        XCTAssertTrue(field == .correct(""))
    }
    
    func test_validateExpiryDate_withValidDate_shouldBeCorrect_score_1() {
        // Given
        var field = CardInputViewModel.Field.correct("12/25")
        
        // When
        sut.validateExpiryDate(&field)
        
        // Then
        XCTAssertTrue(field == .correct("12/25"))
    }
    
    func test_validateExpiryDate_withInvalidMonth_shouldBeIncorrect_score_1() {
        // Given
        var field = CardInputViewModel.Field.correct("13/25")
        
        // When
        sut.validateExpiryDate(&field)
        
        // Then
        XCTAssertTrue(field == .incorrect("13/25"))
    }
    
    func test_validateExpiryDate_withNonNumericCharacters_shouldBeIncorrect_score_1() {
        // Given
        var field = CardInputViewModel.Field.correct("AB/CD")
        
        // When
        sut.validateExpiryDate(&field)
        
        // Then
        XCTAssertTrue(field == .incorrect("AB/CD"))
    }
    
    func test_validateExpiryDate_withPastYear_shouldBeIncorrect_score_1() {
        // Given
        var field = CardInputViewModel.Field.correct("12/20")
        
        // When
        sut.validateExpiryDate(&field)
        
        // Then
        XCTAssertTrue(field == .incorrect("12/20"))
    }
    
    func test_validateExpiryDate_withIncompleteDate_shouldBeCorrect_score_1() {
        // Given
        var field = CardInputViewModel.Field.correct("12/")
        
        // When
        sut.validateExpiryDate(&field)
        
        // Then
        XCTAssertTrue(field == .correct("12/"))
    }
    
    func test_validateCVV_withEmptyString_shouldBeCorrect_score_1() {
        // Given
        var field = CardInputViewModel.Field.correct("")
        
        // When
        sut.validateCVV(&field)
        
        // Then
        XCTAssertTrue(field == .correct(""))
    }
    
    func test_validateCVV_withValid3Digits_shouldBeCorrect_score_1() {
        // Given
        var field = CardInputViewModel.Field.correct("123")
        
        // When
        sut.validateCVV(&field)
        
        // Then
        XCTAssertTrue(field == .correct("123"))
    }
    
    func test_validateCVV_withMoreThan3Digits_shouldBeIncorrect_score_1() {
        // Given
        var field = CardInputViewModel.Field.correct("1234")
        
        // When
        sut.validateCVV(&field)
        
        // Then
        XCTAssertTrue(field == .incorrect("1234"))
    }
    
    func test_validateCVV_withNonNumericCharacters_shouldBeIncorrect_score_1() {
        // Given
        var field = CardInputViewModel.Field.correct("12A")
        
        // When
        sut.validateCVV(&field)
        
        // Then
        XCTAssertTrue(field == .incorrect("12A"))
    }
    
    func test_calculateButtonState_withIncorrectFields_shouldReturnHiddenState_score_1() {
        // Given
        let viewModel = CardInputViewModel(
            cardNumber: .incorrect("")
        )
        
        // When
        let state = sut.calculateButtonState(with: viewModel)
        
        // Then
        XCTAssertEqual(state, .hidden)
    }
    
    func test_calculateButtonState_withCorrectIncompletedFields_shouldReturnDisabledState_score_1() {
        // Given
        let viewModel = CardInputViewModel()
        
        // When
        let state = sut.calculateButtonState(with: viewModel)
        
        // Then
        XCTAssertEqual(state, .disabled)
    }
    
    func test_calculateButtonState_withCorrectCompletedFields_shouldReturnEnabledState_score_2() {
        // Given
        let viewModel = CardInputViewModel(
            cardNumber: .correct("1234 1234 1234 1234"),
            expiryDate: .correct("01/25"),
            cvv: .correct("123")
        )
        
        // When
        let state = sut.calculateButtonState(with: viewModel)
        
        // Then
        XCTAssertEqual(state, .enabled)
    }
}

private extension CardInputViewModel.Field {
    static func == (lhs: CardInputViewModel.Field, rhs: CardInputViewModel.Field) -> Bool {
        switch (lhs, rhs) {
        case (.correct(let lhsValue), .correct(let rhsValue)),
            (.incorrect(let lhsValue), .incorrect(let rhsValue)):
            return lhsValue == rhsValue
        default:
            return false
        }
    }
}
