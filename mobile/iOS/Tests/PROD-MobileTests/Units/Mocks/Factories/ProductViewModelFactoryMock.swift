//
//  ProductViewModelFactoryMock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 07.01.2025.
//

import AppBase

final class ProductViewModelFactoryMock: IProductViewModelFactory {
    
    var invokedMakeProductViewModel = false
    var invokedMakeProductViewModelCount = 0
    var invokedMakeProductParameters: (userInfo: UserInfo, bonuses: [Bonus], product: Product)?
    var invokedMakeProductParametersList = [(userInfo: UserInfo, bonuses: [Bonus], product: Product)]()
    var stubbedMakeProductViewModelResult = ProductViewModel.mock()
    
    func makeProductViewModel(userInfo: UserInfo, bonuses: [Bonus], product: Product) -> ProductViewModel {
        invokedMakeProductViewModel = true
        invokedMakeProductViewModelCount += 1
        invokedMakeProductParameters = (userInfo, bonuses, product)
        invokedMakeProductParametersList.append((userInfo, bonuses, product))
        return stubbedMakeProductViewModelResult
    }
    
    var invokedMakeCartViewModel = false
    var invokedMakeCartViewModelCount = 0
    var invokedMakeCartParameters: (product: Product, count: Int)?
    var invokedMakeCartParametersList = [(product: Product, count: Int)]()
    var stubbedMakeCartViewModelResult = CartProductViewModel.mock()
    
    func makeCartViewModel(product: Product, count: Int) -> CartProductViewModel {
        invokedMakeCartViewModel = true
        invokedMakeCartViewModelCount += 1
        invokedMakeCartParameters = (product, count)
        invokedMakeCartParametersList.append((product, count))
        return stubbedMakeCartViewModelResult
    }
    
    var invokedMakeTotalCartViewModel = false
    var invokedMakeTotalCartViewModelCount = 0
    var invokedMakeTotalCartParameters: (price: Double, weight: Double)?
    var invokedMakeTotalCartParametersList = [(price: Double, weight: Double)]()
    var stubbedMakeTotalCartViewModelResult = TotalCartViewModel.mock()
    
    func makeTotalCartViewModel(price: Double, weight: Double) -> TotalCartViewModel {
        invokedMakeTotalCartViewModel = true
        invokedMakeTotalCartViewModelCount += 1
        invokedMakeTotalCartParameters = (price, weight)
        invokedMakeTotalCartParametersList.append((price, weight))
        return stubbedMakeTotalCartViewModelResult
    }
    
    var invokedMakeBonusViewModel = false
    var invokedMakeBonusViewModelCount = 0
    var invokedMakeBonusParameters: (cashback: Double, bonus: Double)?
    var invokedMakeBonusParametersList = [(cashback: Double, bonus: Double)]()
    var stubbedMakeBonusViewModelResult = BonusViewModel.mock()
    
    func makeBonusViewModel(cashback: Double, bonus: Double) -> BonusViewModel {
        invokedMakeBonusViewModel = true
        invokedMakeBonusViewModelCount += 1
        invokedMakeBonusParameters = (cashback, bonus)
        invokedMakeBonusParametersList.append((cashback, bonus))
        return stubbedMakeBonusViewModelResult
    }
}
