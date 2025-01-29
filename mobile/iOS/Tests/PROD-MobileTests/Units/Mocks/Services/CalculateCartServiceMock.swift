//
//  CalculateCartServiceMock.swift
//  PROD-MobileTests
//
//  Created by Arina Kolganova on 17.01.2025.
//

import AppBase

final class CalculateCartServiceMock: ICalculateCartService {
    let calculateCartService: ICalculateCartService

    init(calculateCartService: ICalculateCartService) {
        self.calculateCartService = calculateCartService
    }

    var invokedCalculateCash = false
    var invokedCalculateCashCount = 0
    var invokedCalculateCashParameters: [CartProduct] = []
    var stubbedCalculateCashResult = 0.0

    func calculateCash(for products: [CartProduct]) -> Double {
        invokedCalculateCash = true
        invokedCalculateCashCount += 1
        stubbedCalculateCashResult = calculateCartService.calculateCash(for: products)
        return stubbedCalculateCashResult
    }

    var invokedCalculateWeight = false
    var invokedCalculateWeightCount = 0
    var invokedCalculateWeightParameters: [CartProduct] = []
    var stubbedCalculateWeightResult = 0.0

    func calculateWeight(for products: [CartProduct]) -> Double {
        invokedCalculateWeight = true
        invokedCalculateWeightCount += 1
        stubbedCalculateWeightResult = calculateCartService.calculateWeight(for: products)
        return stubbedCalculateWeightResult
    }

    var invokedCalculateBonus = false
    var invokedCalculateBonusCount = 0
    var invokedCalculateBonusParameters:(products: [CartProduct], bonuses: [Bonus], userInfo: UserInfo)?
    var stubbedCalculateBonusResult: (bonuses: Double, cashback: Double) = (0.0, 0.0)

    func calculateBonus(for products: [CartProduct], bonuses: [Bonus], userInfo: UserInfo) -> (Double, Double) {
        invokedCalculateBonus = true
        invokedCalculateBonusCount += 1
        stubbedCalculateBonusResult = calculateCartService.calculateBonus(for: products, bonuses: bonuses, userInfo: userInfo)
        return stubbedCalculateBonusResult
    }
}
