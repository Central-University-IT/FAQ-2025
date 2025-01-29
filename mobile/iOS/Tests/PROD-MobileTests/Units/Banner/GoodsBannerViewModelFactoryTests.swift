//
//  GoodsBannerViewModelFactoryTests.swift
//  PROD-MobileTests
//
//  Created by m.titor on 03.01.2025.
//

import XCTest
import AppBase
@testable import Solution

final class GoodsBannerViewModelFactoryTests: XCTestCase {
    
    // Mocks
    private var imageFactoryMock: ImageFactoryMock!
    
    // SUT
    private var sut: IGoodsBannerViewModelFactory!
    
    // MARK: - XCTestCase
    
    override func setUp() {
        super.setUp()
        
        imageFactoryMock = ImageFactoryMock()
        sut = SolutionAssembly.goodsBannerViewModelFactory(imageFactory: imageFactoryMock)
    }
    
    override func tearDown() {
        imageFactoryMock = nil
        sut = nil
        
        super.tearDown()
    }
    
    // MARK: - Tests
    
    func test_bannerState_whenSmallBannerPriorityIsBigger_shouldMatchSmallComesFirst_score_1() {
        // Given
        let goodsBanner = GoodsBanner.stub(largeBannerPriority: 0, smallBannerPriority: 1)
        
        // When
        let viewModel = sut.makeViewModel(banner: goodsBanner)
        
        // Then
        XCTAssertEqual(viewModel.state, .smallComesFirst)
    }
    
    func test_bannerState_whenLargeBannerPriorityIsBigger_shouldMatchLargeComesFirst_score_1() {
        // Given
        let goodsBanner = GoodsBanner.stub(largeBannerPriority: 1, smallBannerPriority: 0)
        
        // When
        let viewModel = sut.makeViewModel(banner: goodsBanner)
        
        // Then
        XCTAssertEqual(viewModel.state, .largeComesFirst)
    }
    
    func test_bannerState_withEqualPriorities_shouldMatchLargeComesFirst_score_1() {
        // Given
        let goodsBanner = GoodsBanner.stub(largeBannerPriority: 0, smallBannerPriority: 0)
        
        // When
        let viewModel = sut.makeViewModel(banner: goodsBanner)
        
        // Then
        XCTAssertEqual(viewModel.state, .largeComesFirst)
    }
    
    func test_bannerState_withoutSmallBanner_shouldMatchLargeOnly_score_1() {
        // Given
        let goodsBanner = GoodsBanner.stub(largeBannerPriority: 0, smallBannerPriority: nil)
        
        // When
        let viewModel = sut.makeViewModel(banner: goodsBanner)
        
        // Then
        XCTAssertEqual(viewModel.state, .largeOnly)
    }
    
    func test_largeBannerTitle_shouldMatchViewModelTitle_score_1() {
        // Given
        let largeBannerTitle = "Стань крутым iOS разработчиком!"
        let goodsBanner = GoodsBanner.stub(largeBannerTitle: largeBannerTitle, largeBannerBonusValue: nil, largeBannerBonusPostfix: nil)
        
        // When
        let viewModel = sut.makeViewModel(banner: goodsBanner)
        
        // Then
        XCTAssertEqual(viewModel.largeBanner.titleLabelText, largeBannerTitle)
    }
    
    func test_largeBannerDescription_shouldMatchViewModelDescription_score_1() {
        // Given
        let largeBannerDescription = "Участвуй в олимпиаде PROD!"
        let goodsBanner = GoodsBanner.stub(largeBannerDescription: largeBannerDescription)
        
        // When
        let viewModel = sut.makeViewModel(banner: goodsBanner)
        
        // Then
        XCTAssertEqual(viewModel.largeBanner.descriptionLabelText, largeBannerDescription)
    }
    
    func test_smallBannerRightLabel_shouldMatchViewModelRightLabel_score_1() {
        // Given
        let smallBannerRightLabel = "Акция"
        let goodsBanner = GoodsBanner.stub(smallBannerRightLabel: smallBannerRightLabel)
        
        // When
        let viewModel = sut.makeViewModel(banner: goodsBanner)
        
        // Then
        XCTAssertEqual(viewModel.smallBanner?.rightLabelText, smallBannerRightLabel)
    }
    
    func test_smallBannerLeftLabel_shouldMatchViewModelLeftLabel_score_1() {
        // Given
        let smallBannerLeftLabel = "2 в 1"
        let goodsBanner = GoodsBanner.stub(smallBannerLeftLabel: smallBannerLeftLabel)
        
        // When
        let viewModel = sut.makeViewModel(banner: goodsBanner)
        
        // Then
        XCTAssertEqual(viewModel.smallBanner?.leftLabelText, smallBannerLeftLabel)
    }
    
    func test_largeBannerTitle_withBonus_shouldIncludeBonusInTitle_score_1() {
        // Given
        let goodsBanner = GoodsBanner.stub(
            largeBannerTitle: "Кэшбэк на манго",
            largeBannerBonusValue: 123,
            largeBannerBonusPostfix: " баллов"
        )
        
        // When
        let viewModel = sut.makeViewModel(banner: goodsBanner)
        
        // Then
        XCTAssertEqual(viewModel.largeBanner.titleLabelText, "Кэшбэк на манго 123 баллов")
    }
    
    func test_largeBannerTitle_withoutBonus_shouldMatchOriginalTitle_score_1() {
        // Given
        let goodsBanner = GoodsBanner.stub(
            largeBannerTitle: "Кэшбэк на манго",
            largeBannerBonusValue: nil,
            largeBannerBonusPostfix: nil
        )
        
        // When
        let viewModel = sut.makeViewModel(banner: goodsBanner)
        
        // Then
        XCTAssertEqual(viewModel.largeBanner.titleLabelText, "Кэшбэк на манго")
    }
    
    func test_largeBannerDescription_withBonus_shouldFormatDescriptionCorrectly_score_1() {
        // Given
        let goodsBanner = GoodsBanner.stub(
            largeBannerDescription: "Купите до 2 кг Манго и получите %@",
            largeBannerBonusValue: 123,
            largeBannerBonusPostfix: " баллов"
        )
        
        // When
        let viewModel = sut.makeViewModel(banner: goodsBanner)
        
        // Then
        XCTAssertEqual(viewModel.largeBanner.descriptionLabelText, "Купите до 2 кг Манго и получите 123 баллов")
    }
    
    func test_largeBannerDescription_withoutBonus_shouldNotReplacePlaceholder_score_1() {
        // Given
        let goodsBanner = GoodsBanner.stub(
            largeBannerDescription: "Купите до 2 кг Манго и получите %@",
            largeBannerBonusValue: nil,
            largeBannerBonusPostfix: nil
        )
        
        // When
        let viewModel = sut.makeViewModel(banner: goodsBanner)
        
        // Then
        XCTAssertTrue(
            viewModel.largeBanner.descriptionLabelText == "Купите до 2 кг Манго и получите " ||
            viewModel.largeBanner.descriptionLabelText == "Купите до 2 кг Манго и получите %@"
        )
    }
}
