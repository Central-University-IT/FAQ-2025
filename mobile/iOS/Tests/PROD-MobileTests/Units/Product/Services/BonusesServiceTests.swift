//
//  BonusesServiceTests.swift
//  PROD-Mobile
//
//  Created by Kuznetsov Mikhail on 17.01.2025.
//

import Foundation
import XCTest
import AppBase
@testable import Solution

final class BonusesServiceTests: XCTestCase {
    
    private var sut: IBonusesService!
    private var networkService: NetworkingServiceMock!
    private var storage: PersistenceStorageMock!
    
    override func setUp() {
        networkService = NetworkingServiceMock()
        storage = PersistenceStorageMock()
        
        sut = SolutionAssembly.bonusesService(networkService: networkService, storage: storage)
    }
    
    override func tearDown() {
        networkService = nil
        storage = nil
        sut = nil
    }
    
    func test_load_score_4() throws {
        
        networkService.stubbedLoadCompletionResult = .success(JSONStub.bonusesResponse!)
        
        var bonuses: [Bonus] = []
        var callCount = 0
        sut.loadBonuses { result in
            callCount += 1
            bonuses = (try? result.get()) ?? []
        }
        
        XCTAssertEqual(callCount, 1)
        
        XCTAssertEqual(bonuses.count, 4)
        XCTAssertEqual(bonuses[0].id, "bb1")
        XCTAssertEqual(bonuses[0].value, 0.1)
        XCTAssertEqual(bonuses[0].type, .cashback)
        XCTAssertNotNil(bonuses[0].availableDate)
        XCTAssertNil(bonuses[0].promotionExtra)
        
        XCTAssertEqual(bonuses[1].id, "bb2")
        XCTAssertEqual(bonuses[1].value, 200)
        XCTAssertEqual(bonuses[1].type, .points)
        XCTAssertNil(bonuses[1].availableDate)
        XCTAssertEqual(bonuses[1].promotionExtra?.label, "PREMIUM")
        XCTAssertEqual(bonuses[1].promotionExtra?.baseColor, "5222FF")
        XCTAssertEqual(bonuses[1].promotionExtra?.tintColor, "FFFFFF")
        
        
        XCTAssertEqual(networkService.invokedLoadCount, 1)
        let netParams = networkService.invokedLoadParametersList[0]

        XCTAssertEqual(netParams.request.baseUrl, "https://prodcontest-ios.ru")
        XCTAssertEqual(netParams.request.methodPath, "/bonuses/all")
    }
    
    func test_readCache_score_4() throws {
        let userId = "12313"
        let bonusesStub = [Bonus.mock(id: "123"), Bonus.mock(id: "234")]
        storage.stubbedFetchResult = bonusesStub

        var bonuses: [Bonus] = []
        var callCount = 0
        sut.loadBonuses { result in
            callCount += 1
            bonuses = (try? result.get()) ?? []
        }

        XCTAssertEqual(callCount, 1)
        
        XCTAssertEqual(bonuses.count, bonusesStub.count)
        XCTAssertEqual(bonuses[0].id, bonusesStub[0].id)
        XCTAssertEqual(bonuses[1].id, bonusesStub[1].id)

        XCTAssertEqual(networkService.invokedLoadCount, 0)
        XCTAssertEqual(storage.invokedFetchCount, 1)
    }
    
    func test_writeCache_score_4() throws {
        let userId = "akhsdg"

        networkService.stubbedLoadCompletionResult = .success(JSONStub.bonusesResponse!)

        sut.loadBonuses { _ in }

        XCTAssertEqual(networkService.invokedLoadCount, 1)
        XCTAssertEqual(storage.invokedFetchCount, 1)
        XCTAssertEqual(storage.invokedSaveCount, 4)
        guard let items = storage.invokedSaveParametersList.map(\.item) as? [Bonus] else {
            return XCTFail()
        }

        XCTAssertEqual(items.count, 4)
        XCTAssertEqual(items[0].id, "bb1")
        XCTAssertEqual(items[0].value, 0.1)
        XCTAssertEqual(items[0].type, .cashback)
        XCTAssertNotNil(items[0].availableDate)
        XCTAssertNil(items[0].promotionExtra)
        
        XCTAssertEqual(items[1].id, "bb2")
        XCTAssertEqual(items[1].value, 200)
        XCTAssertEqual(items[1].type, .points)
        XCTAssertNil(items[1].availableDate)
        XCTAssertEqual(items[1].promotionExtra?.label, "PREMIUM")
        XCTAssertEqual(items[1].promotionExtra?.baseColor, "5222FF")
        XCTAssertEqual(items[1].promotionExtra?.tintColor, "FFFFFF")

    }
}

private enum JSONStub {
    static let bonusesResponse = """
    [
        {
            "id": "bb1",
            "type": "cashback",
            "value": 0.1,
            "available_due_to": "2025-04-23T18:25:43.511Z"
        },
        {
            "id": "bb2",
            "type": "points",
            "value": 200,
            "promotion_extra": {
                "base_color": "5222FF",
                "tint_color": "FFFFFF",
                "label": "PREMIUM"
            }
        },
        {
            "id": "bb3",
            "type": "cashback",
            "value": 0.05,
        },
        {
            "id": "bb4",
            "type": "points",
            "value": 1000,
        }
    ]

    """.data(using: .utf8)
}


