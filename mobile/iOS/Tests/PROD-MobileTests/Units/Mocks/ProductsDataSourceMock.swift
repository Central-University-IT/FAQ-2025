//
//  ProductsDataSourceMock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 15.01.2025.
//

import AppBase

final class ProductsDataSourceMock: IProductsDataSource {
    
    var invokedGetData = false
    var invokedGetDataCount = 0
    var invokedGetDataParameters: (goods: [Product], userInfo: UserInfo, bonuses: [Bonus])?
    var invokedGetDataParametersList = [(goods: [Product], userInfo: UserInfo, bonuses: [Bonus])]()
    var stubbedGetDataResult = [ProductViewModel]()
    
    func getData(goods: [Product], userInfo: UserInfo, bonuses: [Bonus]) -> [ProductViewModel] {
        invokedGetData = true
        invokedGetDataCount += 1
        invokedGetDataParameters = (goods, userInfo, bonuses)
        invokedGetDataParametersList.append((goods, userInfo, bonuses))
        return stubbedGetDataResult
    }
}
