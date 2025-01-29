//
//  ProductViewModelFactoryTests.swift
//  Solution
//
//  Created by a yatsenko on 02.01.2025.
//

import Foundation
import XCTest
import AppBase
@testable import Solution

final class ProductViewModelFactoryTests: XCTestCase {
    
    private var sut: IProductViewModelFactory!
    private var imageFactoryMock: ImageFactoryMock!

    override func setUp() {
        imageFactoryMock = ImageFactoryMock()
        
        sut = SolutionAssembly.productViewModelFactory(imageFactory: imageFactoryMock)
    }
    
    override func tearDown() {
        imageFactoryMock = nil
        
        sut = nil
    }
    
    func test_name_score_2() {
        // given
        let name = "name"
        let product = Product.mock(name: name)
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: UserInfo.mock(), bonuses: [], product: product)
        
        // then
        XCTAssertEqual(viewModel.name, name)
    }
    
    func test_image_score_2() {
        // given
        let imageId = "foo"
        let product = Product.mock(imageId: imageId)
        
        // when
        _ = sut.makeProductViewModel(userInfo: UserInfo.mock(), bonuses: [], product: product)
        
        // then
        XCTAssertEqual(imageFactoryMock.invokedGoodsItemImageCount, 1)
        XCTAssertEqual(imageFactoryMock.invokedGoodsItemImageParameters?.id, imageId)
    }
    
    func test_weight_score_2() {
        // given
        let itemCountity = Product.ItemCountity(type: .kilo, value: 100)
        let product = Product.mock(itemCountity: itemCountity)
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: UserInfo.mock(), bonuses: [], product: product)
        
        // then
        XCTAssertEqual(viewModel.weight, "\(itemCountity.value) кг")
    }
    
    func test_price_score_3() {
        // given
        let cost = 150.0
        let product = Product.mock(cost: cost)
        let quantity = 2
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: UserInfo.mock(), bonuses: [], product: product)
        
        // then
        let buttonsCount = "\(quantity) шт. = \(Int(cost) * quantity) ₽"
        XCTAssertEqual(viewModel.price, "\(Int(cost)) ₽")
        XCTAssertEqual(viewModel.buttonsText(quantity), buttonsCount)
    }
    
    func test_newBadge_score_3() {
        // given
        let isNew = true
        let product = Product.mock(isNew: isNew)
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: UserInfo.mock(), bonuses: [], product: product)
        
        // then
        XCTAssertEqual(viewModel.bottomBadge?.label, "Новинка")
        XCTAssertEqual(viewModel.bottomBadge?.tintColor, "F8AA1B")
        XCTAssertEqual(viewModel.bottomBadge?.label, "Новинка")
    }
    
    func test_highScore_score_3() {
        // given
        let product = Product.mock(popularity: 100)
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: UserInfo.mock(), bonuses: [], product: product)
        
        // then
        XCTAssertEqual(viewModel.score, 3)
    }
    
    func test_lowScore_score_3() {
        // given
        let product = Product.mock(popularity: 20)
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: UserInfo.mock(), bonuses: [], product: product)
        
        // then
        XCTAssertEqual(viewModel.score, 0)
    }
    
    func test_highScoreWithRatingAndHilfgPopularity_score_3() {
        // given
        let product = Product.mock(popularity: 60, rating: 4.5)
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: UserInfo.mock(), bonuses: [], product: product)
        
        // then
        XCTAssertEqual(viewModel.score, 2)
    }
    
    func test_highScoreWithRatingAndLowPopularity_score_3() {
        // given
        let product = Product.mock(popularity: 10, rating: 4.5)
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: UserInfo.mock(), bonuses: [], product: product)
        
        // then
        XCTAssertEqual(viewModel.score, 0)
    }
    
    func test_lowScoreWithRating_score_3() {
        // given
        let product = Product.mock(rating: 2)
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: UserInfo.mock(), bonuses: [], product: product)
        
        // then
        XCTAssertEqual(viewModel.score, -1)
    }
    
    func test_scoreWithFavourites_score_3() {
        // given
        let id = "foo"
        let product = Product.mock(id: id, rating: 2)
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: UserInfo.mock(favourites: [id]), bonuses: [], product: product)
        
        // then
        XCTAssertEqual(viewModel.score, 1)
        XCTAssertEqual(viewModel.bottomBadge?.label, "Любимое ❤️")
    }
    
    func test_scoreWithHistory_score_3() {
        // given
        let category = "foo"
        let product = Product.mock(category: category)
        let userInfo = UserInfo.mock(lastGoodsCat: [category, "bar", "baz"])
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: userInfo, bonuses: [], product: product)
        
        // then
        XCTAssertEqual(viewModel.score, 2)
    }
    
    func test_notActiveBonus_score_2() {
        // given
        let id = "foo"
        let userInfo = UserInfo.mock(availableBonuses: [id])
        let bonus = Bonus.mock(id: id, type: .points, value: 1000)
        let product = Product.mock(bonusIds: [])
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: userInfo, bonuses: [bonus], product: product)
        
        // then
        XCTAssertNil(viewModel.topBadge)
    }
    
    func test_notAvailableBonus_score_2() {
        // given
        let id = "foo"
        let userInfo = UserInfo.mock(availableBonuses: [])
        let bonus = Bonus.mock(id: id, type: .points, value: 1000)
        let product = Product.mock(bonusIds: [id])
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: userInfo, bonuses: [bonus], product: product)
        
        // then
        XCTAssertNil(viewModel.topBadge)
    }
    
    func test_cashback_score_3() {
        // given
        let id = "foo"
        let userInfo = UserInfo.mock(availableBonuses: [id])
        let bonus = Bonus.mock(id: id, type: .cashback, value: 0.1)
        let product = Product.mock(bonusIds: [id])
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: userInfo, bonuses: [bonus], product: product)
        
        // then
        XCTAssertEqual(viewModel.topBadge?.label, "Кэшбэк \(Int(bonus.value * 100))%")
    }
    
    func test_promotionСashback_score_3() {
        // given
        let id = "foo"
        let userInfo = UserInfo.mock(availableBonuses: [id])
        let label = "label"
        let promotionExtra = Bonus.Promotion(baseColor: "bar", tintColor: "baz", label: label)
        let bonus = Bonus.mock(
            id: id,
            type: .cashback,
            value: 0.1,
            promotionExtra: promotionExtra
        )
        let product = Product.mock(bonusIds: [id])
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: userInfo, bonuses: [bonus], product: product)
        
        // then
        XCTAssertEqual(viewModel.topBadge?.label, "\(label) • \(Int(bonus.value * 100))%")
        XCTAssertEqual(viewModel.topBadge?.baseColor, promotionExtra.baseColor)
        XCTAssertEqual(viewModel.topBadge?.tintColor, promotionExtra.tintColor)
    }
    
    func test_points_score_3() {
        // given
        let id = "foo"
        let userInfo = UserInfo.mock(availableBonuses: [id])
        let bonus = Bonus.mock(id: id, type: .points, value: 1000)
        let product = Product.mock(bonusIds: [id])
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: userInfo, bonuses: [bonus], product: product)
        
        // then
        XCTAssertEqual(viewModel.topBadge?.label, "\(Int(bonus.value)) баллов")
    }
    
    func test_promotionPoints_score_3() {
        // given
        let id = "foo"
        let userInfo = UserInfo.mock(availableBonuses: [id])
        let label = "label"
        let promotionExtra = Bonus.Promotion(baseColor: "bar", tintColor: "baz", label: label)
        let bonus = Bonus.mock(
            id: id,
            type: .points,
            value: 1000,
            promotionExtra: promotionExtra
        )
        let product = Product.mock(bonusIds: [id])
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: userInfo, bonuses: [bonus], product: product)
        
        // then
        XCTAssertEqual(viewModel.topBadge?.label, "\(label) • \(Int(bonus.value)) Б")
        XCTAssertEqual(viewModel.topBadge?.baseColor, promotionExtra.baseColor)
        XCTAssertEqual(viewModel.topBadge?.tintColor, promotionExtra.tintColor)
    }
    
    func test_dateValide_score_3() {
        // given
        let id = "foo"
        let userInfo = UserInfo.mock(availableBonuses: [id])
        
        let isoDateFormatter = ISO8601DateFormatter()
        isoDateFormatter.formatOptions.insert(.withFractionalSeconds)
        let nextMonth = Calendar.current.date(byAdding: .month, value: 1, to: Date())!
        
        let bonus = Bonus.mock(
            id: id,
            type: .points,
            availableDueTo: isoDateFormatter.string(from: nextMonth)
        )
        let product = Product.mock(bonusIds: [id])
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: userInfo, bonuses: [bonus], product: product)
        
        // then
        XCTAssertEqual(viewModel.topBadge?.label, "\(Int(bonus.value)) баллов")
    }
    
    func test_dateExpired_score_2() {
        // given
        let id = "foo"
        let userInfo = UserInfo.mock(availableBonuses: [id])
        let bonus = Bonus.mock(
            id: id,
            type: .points,
            availableDueTo: "2023-04-23T18:25:43.511Z"
        )
        let product = Product.mock()
        
        // when
        let viewModel = sut.makeProductViewModel(userInfo: userInfo, bonuses: [bonus], product: product)
        
        // then
        XCTAssertNil(viewModel.topBadge)
    }
}
