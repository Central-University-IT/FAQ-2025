//
//  GoodsServiceTests.swift
//  PROD-Mobile
//
//  Created by Kuznetsov Mikhail on 17.01.2025.
//

import Foundation
import XCTest
import AppBase
@testable import Solution

final class GoodsServiceTests: XCTestCase {
    
    private var sut: IGoodsService!
    private var networkService: NetworkingServiceMock!
    private var storage: PersistenceStorageMock!
    
    override func setUp() {
        networkService = NetworkingServiceMock()
        storage = PersistenceStorageMock()
        
        sut = SolutionAssembly.goodsService(networkService: networkService, storage: storage)
    }
    
    override func tearDown() {
        networkService = nil
        storage = nil
        sut = nil
    }
    
    func test_load_score_4() throws {
        
        networkService.stubbedLoadCompletionResult = .success(JSONStub.goodsResponse!)
        
        var products: [Product] = []
        var callCount = 0
        sut.loadGoods { result in
            callCount += 1
            products = (try? result.get()) ?? []
        }
        
        XCTAssertEqual(callCount, 1)
        
        XCTAssertEqual(products.count, 4)
        XCTAssertEqual(products[0].id, "prod-1")
        XCTAssertEqual(products[0].name, "Чтобы")
        XCTAssertEqual(products[0].imageId, "goods-1-img")
        XCTAssertEqual(products[0].producer.id, "prodai-31323")
        XCTAssertEqual(products[0].producer.name, "АО Московский хрящекомбинат")
        XCTAssertEqual(products[0].itemCountity.value, 200)
        XCTAssertEqual(products[0].itemCountity.type, .gramm)
        XCTAssertEqual(products[0].cost, 130)
        XCTAssertEqual(products[0].popularity, 100)
        XCTAssertEqual(products[0].category, "HLEB")
        XCTAssertEqual(products[0].isNew, nil)
            
        XCTAssertEqual(products[2].bonusIds, ["31231", "qweq"])

        XCTAssertEqual(networkService.invokedLoadCount, 1)
        let netParams = networkService.invokedLoadParametersList[0]

        XCTAssertEqual(netParams.request.baseUrl, "https://prodcontest-ios.ru")
        XCTAssertEqual(netParams.request.methodPath, "/goods/all")
    }
    
    func test_readCache_score_4() throws {
        let userId = "12313"
        let productsStub = [Product.mock(id: "123"), Product.mock(id: "234")]
        storage.stubbedFetchResult = productsStub

        var products: [Product] = []
        var callCount = 0
        sut.loadGoods { result in
            callCount += 1
            products = (try? result.get()) ?? []
        }

        XCTAssertEqual(callCount, 1)
        
        XCTAssertEqual(products.count, productsStub.count)
        XCTAssertEqual(products[0].id, productsStub[0].id)
        XCTAssertEqual(products[1].id, productsStub[1].id)

        XCTAssertEqual(networkService.invokedLoadCount, 0)
        XCTAssertEqual(storage.invokedFetchCount, 1)
    }
    
    func test_writeCache_score_5() throws {
        let userId = "akhsdg"

        networkService.stubbedLoadCompletionResult = .success(JSONStub.goodsResponse!)

        sut.loadGoods { _ in }

        XCTAssertEqual(networkService.invokedLoadCount, 1)
        XCTAssertEqual(storage.invokedFetchCount, 1)
        XCTAssertEqual(storage.invokedSaveCount, 4)
        guard let items = storage.invokedSaveParametersList.map(\.item) as? [Product] else {
            return XCTFail()
        }

        XCTAssertEqual(items.count, 4)
        XCTAssertEqual(items[0].id, "prod-1")
        XCTAssertEqual(items[0].name, "Чтобы")
        XCTAssertEqual(items[0].imageId, "goods-1-img")
        XCTAssertEqual(items[0].producer.id, "prodai-31323")
        XCTAssertEqual(items[0].producer.name, "АО Московский хрящекомбинат")
        XCTAssertEqual(items[0].itemCountity.value, 200)
        XCTAssertEqual(items[0].itemCountity.type, .gramm)
        XCTAssertEqual(items[0].cost, 130)
        XCTAssertEqual(items[0].popularity, 100)
        XCTAssertEqual(items[0].category, "HLEB")
        XCTAssertEqual(items[0].isNew, nil)
            
        XCTAssertEqual(items[2].bonusIds, ["31231", "qweq"])

    }
}

private enum JSONStub {
    static let goodsResponse = """
    {
        "goods": [
            {
                "id": "prod-1",
                "image_id": "goods-1-img",
                "name": "Чтобы",
                "producer": {
                    "id": "prodai-31323",
                    "name": "АО Московский хрящекомбинат"
                },
                "item_countity": {
                    "type": "gramm",
                    "value": 200
                },
                "cost": 130,
                "popularity": 100,
                "category": "HLEB"
            },
            {
                "id": "prod-2",
                "image_id": "goods-2-img",
                "name": "Лол",
                "producer": {
                    "id": "prodai-31323",
                    "name": "Участник PROD'а"
                },
                "is_new": true,
                "item_countity": {
                    "type": "kilo",
                    "value": 2
                },
                "cost": 2000,
                "popularity": 100,
                "rating": 4.9,
                "category": "HLEB"
            },
            {
                "id": "prod-3",
                "image_id": "goods-3-img",
                "name": "Коко",
                "producer": {
                    "id": "prodai-31323",
                    "name": "ООО Милые Коровки"
                },
                "is_new": true,
                "item_countity": {
                    "type": "kilo",
                    "value": 1
                },
                "cost": 244,
                "popularity": 25,
                "rating": 3.5,
                "bonus_ids": ["31231", "qweq"],
                "category": "MILK"
            },
            {
                "id": "prod-4",
                "image_id": "goods-4-img",
                "name": "Заголовок",
                "producer": {
                    "id": "prodai-31323",
                    "name": "Египетские сельхоз проля Дары Клеопатры"
                },
                "item_countity": {
                    "type": "gramm",
                    "value": 200
                },
                "cost": 130.2506,
                "popularity": 35,
                "rating": 4.5,
                "bonus_ids": ["bonus_13132", "bonus_4243"],
                "category": "FRUITS"
            }
        
        ]
    }
    """.data(using: .utf8)
}



