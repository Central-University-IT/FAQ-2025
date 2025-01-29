//
//  BonusesServiceMock.swift
//  PROD-MobileTests
//
//  Created by Arina Kolganova on 12.01.2025.
//

import AppBase

final class BonusesServiceMock: IBonusesService {

    var invokedService = false
    var invokedLoadServiceCount = 0
    var stubbedLoadServiceCompletionResult: Result<[Bonus], Error>?

    func loadBonuses(completion: @escaping (Result<[Bonus], Error>) -> Void) {
        invokedService = true
        invokedLoadServiceCount += 1
        if let result = stubbedLoadServiceCompletionResult {
            completion(result)
        }
    }
}
