//
//  UserInfoServiceTests.swift
//  PROD-Mobile
//
//  Created by Kuznetsov Mikhail on 17.01.2025.
//

import Foundation
import XCTest
import AppBase
@testable import Solution

final class UserInfoServiceTests: XCTestCase {
    
    private var sut: IUserInfoService!
    private var networkService: NetworkingServiceMock!
    private var storage: PersistenceStorageMock!
    
    override func setUp() {
        networkService = NetworkingServiceMock()
        storage = PersistenceStorageMock()
        
        sut = SolutionAssembly.userInfoService(networkService: networkService, storage: storage)
    }
    
    override func tearDown() {
        networkService = nil
        storage = nil
        sut = nil
    }
    
    func test_load_score_4() throws {
        
        networkService.stubbedLoadCompletionResult = .success(JSONStub.userInfoResponse!)
        
        var user: UserInfo?
        var callCount = 0
        sut.loadUserInfo { result in
            callCount += 1
            user = try? result.get()
        }
        
        let userInfo = try XCTUnwrap(user)
        XCTAssertEqual(callCount, 1)
        
        XCTAssertEqual(userInfo.lastGoodsCat, ["SOME1", "SOME2"])
        XCTAssertEqual(userInfo.availableBonuses, ["bon-1", "bon-2", "bon-3"])
        XCTAssertEqual(userInfo.favourites, ["qwerty090"])
        XCTAssertEqual(userInfo.activityLevel, 1000)
        
        XCTAssertEqual(networkService.invokedLoadCount, 1)
        let netParams = networkService.invokedLoadParametersList[0]

        XCTAssertEqual(netParams.request.baseUrl, "https://prodcontest-ios.ru")
        XCTAssertEqual(netParams.request.methodPath, "/user/user312/goods_info")
    }
    
    func test_noCache_score_4() throws {
        let userId = "akhsdg"

        networkService.stubbedLoadCompletionResult = .success(JSONStub.userInfoResponse!)

        sut.loadUserInfo { _ in }

        XCTAssertEqual(networkService.invokedLoadCount, 1)
        XCTAssertEqual(storage.invokedFetchCount, 0)
        XCTAssertEqual(storage.invokedSaveCount, 0)

    }
}

private enum JSONStub {
    static let userInfoResponse = """
    {
        "last_goods_cat": ["SOME1", "SOME2"],
        "available_bonuses": ["bon-1", "bon-2", "bon-3"],
        "favourites": ["qwerty090"],
        "activity_level": 1000
    }
    """.data(using: .utf8)
}

