//
//  CartPresenterTests.swift
//  PROD-MobileTests
//
//  Created by Arina Kolganova on 17.01.2025.
//

import Foundation
import XCTest
import AppBase
import Solution

final class CartPresenterTests: XCTestCase {

    private var sut: ICartPresenter!
    private var cartServiceMock: CartServiceMock!
    private var bonusesServiceMock: BonusesServiceMock!
    private var userInfoServiceMock: UserInfoServiceMock!
    private var calculateCartServiceMock: CalculateCartServiceMock!
    private var productViewModelFactoryMock: ProductViewModelFactoryMock!
    private var cartViewControllerMock: CartViewControllerMock!
    private var imageFactory: ImageFactoryMock!

    override func setUp() {
        imageFactory = ImageFactoryMock()

        resetAll()
    }

    override func tearDown() {
        sut = nil

        cartServiceMock = nil
    }

    func test_openScreen_cartService_singleProduct_score_1() {
        resetCartService()
        let product = Product.mock(id: "good_13643463-hae")
        cartServiceMock.stubbedLoadCartProductResult = [CartProduct(product: product, count: 3)]
        resetPresenter()

        XCTAssertEqual(1, cartServiceMock.invokedLoadCartProductCount)
        XCTAssertEqual(1, cartServiceMock.stubbedLoadCartProductResult.count)
        XCTAssertEqual("good_13643463-hae", cartServiceMock.stubbedLoadCartProductResult.first?.product.id)
        XCTAssertEqual(3, cartServiceMock.stubbedLoadCartProductResult.first?.count)
    }

    func test_openScreen_cartService_multiProduct_score_1() {
        resetCartService()
        let firstProduct = Product.mock(id: "good_13643463-dao")
        let secondProduct = Product.mock(id: "good_1364346-hie")
        cartServiceMock.stubbedLoadCartProductResult = [
            CartProduct(product: firstProduct, count: 3),
            CartProduct(product: secondProduct, count: 10),
        ]
        resetPresenter()

        XCTAssertEqual(1, cartServiceMock.invokedLoadCartProductCount)
        XCTAssertEqual(2, cartServiceMock.stubbedLoadCartProductResult.count)
        XCTAssertEqual("good_13643463-dao", cartServiceMock.stubbedLoadCartProductResult.first?.product.id)
        XCTAssertEqual(3, cartServiceMock.stubbedLoadCartProductResult.first?.count)
        XCTAssertEqual("good_1364346-hie", cartServiceMock.stubbedLoadCartProductResult.last?.product.id)
        XCTAssertEqual(10, cartServiceMock.stubbedLoadCartProductResult.last?.count)
    }

    func test_openScreen_bonusesService_score_1() {
        resetBonusService()
        resetPresenter()

        XCTAssertEqual(1, bonusesServiceMock.invokedLoadServiceCount)
    }

    func test_openScreen_userInfoService_score_1() {
        resetCartService()
        let firstProduct = Product.mock(id: "good_13643463-dao")
        let secondProduct = Product.mock(id: "good_1364346-hie")
        cartServiceMock.stubbedLoadCartProductResult = [
            CartProduct(product: firstProduct, count: 3),
            CartProduct(product: secondProduct, count: 10),
        ]
        resetPresenter()

        XCTAssertEqual(1, cartServiceMock.invokedLoadCartProductCount)
        XCTAssertEqual(2, cartServiceMock.stubbedLoadCartProductResult.count)
        XCTAssertEqual("good_13643463-dao", cartServiceMock.stubbedLoadCartProductResult.first?.product.id)
        XCTAssertEqual(3, cartServiceMock.stubbedLoadCartProductResult.first?.count)
        XCTAssertEqual("good_1364346-hie", cartServiceMock.stubbedLoadCartProductResult.last?.product.id)
        XCTAssertEqual(10, cartServiceMock.stubbedLoadCartProductResult.last?.count)
    }

    func test_openScreen_cartProductViewModel_score_1() {
        resetProductViewModelFactory()
        let firstProduct = Product.mock(id: "good_13643463-dao")
        let secondProduct = Product.mock(id: "good_1364346-hie")
        cartServiceMock.stubbedLoadCartProductResult = [
            CartProduct(product: firstProduct, count: 3),
            CartProduct(product: secondProduct, count: 10),
        ]
        resetPresenter()

        XCTAssertEqual(2, productViewModelFactoryMock.invokedMakeCartViewModelCount)
        XCTAssertEqual(firstProduct.id, productViewModelFactoryMock.invokedMakeCartParametersList[0].product.id)
        XCTAssertEqual(3, productViewModelFactoryMock.invokedMakeCartParametersList[0].count)
        XCTAssertEqual(secondProduct.id, productViewModelFactoryMock.invokedMakeCartParametersList[1].product.id)
        XCTAssertEqual(10, productViewModelFactoryMock.invokedMakeCartParametersList[1].count)
    }

    private func resetCartService() {
        cartServiceMock = CartServiceMock()
    }

    private func resetBonusService() {
        bonusesServiceMock = BonusesServiceMock()
    }

    private func resetUserInfo() {
        userInfoServiceMock = UserInfoServiceMock()
    }

    private func resetCalculateService() {
        calculateCartServiceMock = CalculateCartServiceMock(calculateCartService: SolutionAssembly.calculateCartService())
    }

    private func resetProductViewModelFactory() {
        productViewModelFactoryMock = ProductViewModelFactoryMock()
    }

    private func resetPresenter() {
        sut = SolutionAssembly.cartPresenter(
            cartViewFactory: SolutionAssembly.cartViewFactory(),
            productViewModelFactory: productViewModelFactoryMock,
            cartService: cartServiceMock,
            bonusesService: bonusesServiceMock,
            calculateCartService: calculateCartServiceMock,
            userInfoService: userInfoServiceMock,
            paymentCartService: PaymentCartService(),
            cartButtonsInteractionModel: SolutionAssembly.cartButtonsViewInteractionModel(cartService: cartServiceMock),
            cardHandler: { UIViewController() }
        )
        cartViewControllerMock = CartViewControllerMock()
        sut.view = cartViewControllerMock
    }

    private func resetAll() {
        resetCartService()
        resetBonusService()
        resetUserInfo()
        resetCalculateService()
        resetProductViewModelFactory()
        resetPresenter()
    }
}
