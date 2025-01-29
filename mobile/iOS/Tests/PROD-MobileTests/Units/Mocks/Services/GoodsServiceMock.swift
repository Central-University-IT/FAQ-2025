//
//  GoodsServiceMock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 07.01.2025.
//

import AppBase

final class GoodsServiceMock: IGoodsService {
  
    var invokedLoadGoods = false
    var invokedLoadGoodsCount = 0
    var stubbedLoadGoodsCompletionResult: Result<[Product], Error>?
    
    func loadGoods(completion: @escaping (Result<[Product], Error>) -> Void) {
        invokedLoadGoods = true
        invokedLoadGoodsCount += 1
        if let result = stubbedLoadGoodsCompletionResult {
            completion(result)
        }
    }
}
