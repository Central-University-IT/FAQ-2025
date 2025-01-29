//
//  GoodsBannerServiceTests.swift
//  PROD-Mobile
//
//  Created by m.titor on 16.01.2025.
//
import XCTest
import AppBase
import ProdMobileCore
@testable import Solution

final class GoodsBannerServiceTests: XCTestCase {
    
    // Mocks
    private var networkServiceMock: NetworkingServiceMock!
    
    // SUT
    private var sut: GoodsBannerService!
    
    // MARK: - XCTestCase
    
    override func setUp() {
        super.setUp()
        
        networkServiceMock = NetworkingServiceMock()
        sut = GoodsBannerService(networkService: networkServiceMock)
    }
    
    override func tearDown() {
        networkServiceMock = nil
        sut = nil
        
        super.tearDown()
    }
    
    // MARK: - Tests
    
    func test_loadGoodsBanner_score_12() {
        // Given
        networkServiceMock.stubbedLoadCompletionResult = .success(JSONStubs.bannerResponse)
        let stubbedGodsBanner = GoodsBanner.stub()
        
        // When & Then
        sut.loadGoodsBanner { result in
            switch result {
            case .success(let goodsBanner):
                XCTAssertEqual(stubbedGodsBanner.largeBanner.title, goodsBanner.largeBanner.title)
                XCTAssertEqual(stubbedGodsBanner.largeBanner.description, goodsBanner.largeBanner.description)
                XCTAssertEqual(stubbedGodsBanner.largeBanner.imageId, goodsBanner.largeBanner.imageId)
                XCTAssertEqual(stubbedGodsBanner.largeBanner.priority, goodsBanner.largeBanner.priority)
                XCTAssertEqual(stubbedGodsBanner.largeBanner.bonus?.postfix, goodsBanner.largeBanner.bonus?.postfix)
                XCTAssertEqual(stubbedGodsBanner.largeBanner.bonus?.value, goodsBanner.largeBanner.bonus?.value)
                XCTAssertEqual(stubbedGodsBanner.smallBanner?.priority, goodsBanner.smallBanner?.priority)
                XCTAssertEqual(stubbedGodsBanner.smallBanner?.leftLabel, goodsBanner.smallBanner?.leftLabel)
                XCTAssertEqual(stubbedGodsBanner.smallBanner?.rightLabel, goodsBanner.smallBanner?.rightLabel)
            case .failure(let error):
                XCTFail("Failed load data with error: \(error.localizedDescription)")
            }
        }
        
        XCTAssertTrue(networkServiceMock.invokedLoad)
        XCTAssertEqual(networkServiceMock.invokedLoadCount, 1)
    }
}


private enum JSONStubs {
    
    static let bannerResponse = """
    {
        "large": {
            "priority": 1,
            "image_id": "banner-1-img",
            "title": "Кэшбэк на манго",
            "description": "Купите до 2 кг Манго и получите %@",
            "bonus": {
                "value": 123,
                "postfix": " баллов"
            }
        },
        "small": {
            "priority": 0,
            "right_label": "Акция",
            "left_label": "2 в 1"
        }
    }
    """.data(using: .utf8)!
}
