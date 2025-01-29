//
//  SnapshotSolutionTest.swift
//  PROD-MobileTests
//
//  Created by Kuznetsov Mikhail on 21.12.2024.
//

import UIKit
import FBSnapshotTestCase
@testable import AppBase
@testable import Solution
import ProdMobileCore

class SnapshotSolutionTest: FBSnapshotTestCase {

    override func setUp() {
        super.setUp()

        recordMode = false
    }

    func test_GoodsBannerView_whenLargeComesFirst_score_10() {

        let imageFactoryMock = ImageFactory()
        let bannerData = GoodsBanner.stub()
        let viewModel = SolutionAssembly.goodsBannerViewModelFactory(imageFactory: imageFactoryMock).makeViewModel(banner: bannerData)
        let bannerView = GoodsBannerView()

        bannerView.configure(with: viewModel)
        bannerView.frame = CGRect(x: 0, y: 0, width: 382, height: 108)

        verify(view: bannerView)
    }

    func test_GoodsBannerView_whenSmallComesFirst_score_10() {

        let imageFactoryMock = ImageFactory()
        let bannerData = GoodsBanner.stub(largeBannerPriority: 0, smallBannerPriority: 1)
        let viewModel = SolutionAssembly.goodsBannerViewModelFactory(imageFactory: imageFactoryMock).makeViewModel(banner: bannerData)
        let bannerView = GoodsBannerView()

        bannerView.configure(with: viewModel)
        bannerView.frame = CGRect(x: 0, y: 0, width: 382, height: 108)

        verify(view: bannerView)
    }

    func test_GoodsBannerView_whenOnlyLarge_score_10() {

        let imageFactoryMock = ImageFactory()
        let bannerData = GoodsBanner.stub(smallBannerPriority: nil)
        let viewModel = SolutionAssembly.goodsBannerViewModelFactory(imageFactory: imageFactoryMock).makeViewModel(banner: bannerData)
        let bannerView = GoodsBannerView()

        bannerView.configure(with: viewModel)
        bannerView.frame = CGRect(x: 0, y: 0, width: 382, height: 108)

        verify(view: bannerView)
    }

    func test_CardInputView_withEmptyFields_score_5() {

        let viewModel = CardInputViewModel(
            cardNumber: .correct(""),
            expiryDate: .correct(""),
            cvv: .correct("")
        )
        let cardInputView = CardInputView()

        cardInputView.configure(viewModel: viewModel)
        cardInputView.frame = CGRect(x: 0, y: 0, width: 414, height: 186)

        verify(view: cardInputView)
    }

    func test_CardInputView_withIncorrectCardNumber_score_5() {

        let viewModel = CardInputViewModel(
            cardNumber: .incorrect("2002 7077 1234 PROD"),
            expiryDate: .correct(""),
            cvv: .correct("")
        )
        let cardInputView = CardInputView()

        cardInputView.configure(viewModel: viewModel)
        cardInputView.frame = CGRect(x: 0, y: 0, width: 414, height: 204)

        verify(view: cardInputView)
    }

    func test_CardInputView_withIncorrectExpiryDate_score_5() {

        let viewModel = CardInputViewModel(
            cardNumber: .correct(""),
            expiryDate: .incorrect("12/24"),
            cvv: .correct("")
        )
        let cardInputView = CardInputView()

        cardInputView.configure(viewModel: viewModel)
        cardInputView.frame = CGRect(x: 0, y: 0, width: 414, height: 204)

        verify(view: cardInputView)
    }

    func test_CardInputView_withIncorrectCvv_score_5() {

        let viewModel = CardInputViewModel(
            cardNumber: .correct(""),
            expiryDate: .correct(""),
            cvv: .incorrect("1234")
        )
        let cardInputView = CardInputView()


        cardInputView.configure(viewModel: viewModel)
        cardInputView.frame = CGRect(x: 0, y: 0, width: 414, height: 204)
        cardInputView.setNeedsLayout()
        cardInputView.layoutIfNeeded()

        verify(view: cardInputView)
    }

    func test_CardInputView_withIncorrectFields_score_5() {

        let viewModel = CardInputViewModel(
            cardNumber: .incorrect("1234 5678 9101 PROD"),
            expiryDate: .incorrect("12/24"),
            cvv: .incorrect("1234")
        )
        let cardInputView = CardInputView()

        cardInputView.configure(viewModel: viewModel)
        cardInputView.frame = CGRect(x: 0, y: 0, width: 414, height: 222)

        verify(view: cardInputView)
    }

    func test_productViewWithoutBadges_score_12() {
        let viewModel = makeSomeProduct()
        let cartServiceMock = CartServiceMock()
        let productView = ProductView(cartService: cartServiceMock)
        let buttonsViewInteractionModel = SolutionAssembly.buttonsViewInteractionModel(productId: viewModel.id, cartService: cartServiceMock)

        productView.configure(product: viewModel, buttonsInteractionModel: buttonsViewInteractionModel)
        productView.frame = CGRect(x: 0, y: 0, width: 164, height: 237)

        verify(view: productView)
    }

    func test_productViewWithBottomBadge_score_12() {
        let bottomBadge = ProductViewModel.Badge(label: "Любимое ❤️")
        let viewModel = makeSomeProduct(bottomBadge: bottomBadge)
        let cartServiceMock = CartServiceMock()
        let productView = ProductView(cartService: cartServiceMock)
        let buttonsViewInteractionModel = SolutionAssembly.buttonsViewInteractionModel(productId: viewModel.id, cartService: cartServiceMock)

        productView.configure(product: viewModel, buttonsInteractionModel: buttonsViewInteractionModel)
        productView.frame = CGRect(x: 0, y: 0, width: 164, height: 237)

        verify(view: productView)
    }

    func test_productViewWithTopBadgePromotion_score_13() {
        let topBadge = ProductViewModel.Badge(
            label: "PREMIUM • 10%",
            tintColor: "FFFFFF",
            baseColor: "5222FF"
        )
        let viewModel = makeSomeProduct(topBadge: topBadge)
        let cartServiceMock = CartServiceMock()
        let productView = ProductView(cartService: cartServiceMock)
        let buttonsViewInteractionModel = SolutionAssembly.buttonsViewInteractionModel(productId: viewModel.id, cartService: cartServiceMock)

        productView.configure(product: viewModel, buttonsInteractionModel: buttonsViewInteractionModel)
        productView.frame = CGRect(x: 0, y: 0, width: 164, height: 237)

        verify(view: productView)
    }

    func test_productViewWithBottomAndTopBadge_score_13() {
        let bottomBadge = ProductViewModel.Badge(label: "Любимое ❤️")
        let topBadge = ProductViewModel.Badge(label: "Кэшбэк 10%")
        let viewModel = makeSomeProduct(bottomBadge: bottomBadge, topBadge: topBadge)
        let cartServiceMock = CartServiceMock()
        let productView = ProductView(cartService: cartServiceMock)
        let buttonsViewInteractionModel = SolutionAssembly.buttonsViewInteractionModel(productId: viewModel.id, cartService: cartServiceMock)

        productView.configure(product: viewModel, buttonsInteractionModel: buttonsViewInteractionModel)
        productView.frame = CGRect(x: 0, y: 0, width: 164, height: 237)

        verify(view: productView)
    }

    func test_cartView_withBonuses_score_18() {
        let imageFactory = ImageFactory()
        let product = CartProductViewModel(
            id: "good_13643463-hae",
            name: "Ты мягкий пирожочек",
            totalWeight: "2 кг",
            totalPrice: "2000 ₽",
            priceDescription: "1 шт. (по 2кг)* 2000 ₽",
            image: imageFactory.goodsItemImage(id: "good_13643463-hae")
        )
        let totalCart = TotalCartViewModel(price: "2000 ₽", weight: "2,4 кг")
        let bonuses = BonusViewModel(cashback: "1 000 ₽", bonus: "200 баллов")
        let cartView = CartView(products: [product], cartButtonsInteractionModel: nil)

        cartView.configure(totalCartViewModel: totalCart, bonusViewModel: bonuses)
        cartView.frame = CGRect(x: 0, y: 0, width: 402, height: 660)

        verify(view: cartView)
    }

    func test_cartView_withoutBonuses_score_17() {
        let imageFactory = ImageFactory()
        let product = CartProductViewModel(
            id: "good_13643463-hae",
            name: "Ты мягкий пирожочек",
            totalWeight: "2 кг",
            totalPrice: "2000 ₽",
            priceDescription: "1 шт. (по 2кг)* 2000 ₽",
            image: imageFactory.goodsItemImage(id: "good_13643463-hae")
        )
        let totalCart = TotalCartViewModel(price: "2000 ₽", weight: "2,4 кг")
        let bonuses = BonusViewModel(cashback: "", bonus: "")
        let cartView = CartView(products: [product], cartButtonsInteractionModel: nil)

        cartView.configure(totalCartViewModel: totalCart, bonusViewModel: bonuses)
        cartView.frame = CGRect(x: 0, y: 0, width: 402, height: 660)

        verify(view: cartView)
    }

    func test_cartView_singleProduct_score_18() {
        let imageFactory = ImageFactory()
        let product = CartProductViewModel(
            id: "good_13643463-hae",
            name: "Ты мягкий пирожочек",
            totalWeight: "2 кг",
            totalPrice: "2000 ₽",
            priceDescription: "1 шт. (по 2кг)* 2000 ₽",
            image: imageFactory.goodsItemImage(id: "good_13643463-hae")
        )
        let totalCart = TotalCartViewModel(price: "2000 ₽", weight: "2,4 кг")
        let bonuses = BonusViewModel(cashback: "1 000 ₽", bonus: "200 баллов")
        let cartView = CartView(products: [product], cartButtonsInteractionModel: nil)

        cartView.configure(totalCartViewModel: totalCart, bonusViewModel: bonuses)
        cartView.frame = CGRect(x: 0, y: 0, width: 402, height: 660)

        verify(view: cartView)
    }

    func test_cartView_multiProduct_score_18() {
        let imageFactory = ImageFactory()
        let firstProduct = CartProductViewModel(
            id: "good_13643463-hae",
            name: "Ты мягкий пирожочек",
            totalWeight: "2 кг",
            totalPrice: "2000 ₽",
            priceDescription: "1 шт. (по 2кг)* 2000 ₽",
            image: imageFactory.goodsItemImage(id: "good_13643463-hae")
        )
        let secondProduct = CartProductViewModel(
            id: "good_13643463-dao",
            name: "Ты мягкий пирожочек поменьше",
            totalWeight: "1 кг",
            totalPrice: "1000 ₽",
            priceDescription: "1 шт. (по 1кг)* 1000 ₽",
            image: UIImage()
        )
        let thirdProduct = CartProductViewModel(
            id: "good_13643463-hei",
            name: "Ты мягкий пирожочек еще поменьше",
            totalWeight: "0,5 кг",
            totalPrice: "500 ₽",
            priceDescription: "1 шт. (по 0,5кг)* 500 ₽",
            image: imageFactory.goodsItemImage(id: "good_13643463-hei")
        )
        let totalCart = TotalCartViewModel(price: "2000 ₽", weight: "2,4 кг")
        let bonuses = BonusViewModel(cashback: "100 000 ₽", bonus: "20 000 баллов")
        let cartView = CartView(products: [firstProduct, secondProduct, thirdProduct], cartButtonsInteractionModel: nil)

        cartView.configure(totalCartViewModel: totalCart, bonusViewModel: bonuses)
        cartView.frame = CGRect(x: 0, y: 0, width: 402, height: 660)

        verify(view: cartView)
    }

    private func makeSomeProduct(
        bottomBadge: ProductViewModel.Badge? = nil,
        topBadge: ProductViewModel.Badge? = nil
    ) -> ProductViewModel {
        ProductViewModel.mock(
            name: "Хлеб “Российский”",
            producer: "ао “хлебное поле”",
            weight: "200 г",
            price: "500 ₽",
            image: ImageFactory().goodsItemImage(id: "none"),
            bottomBadge: bottomBadge,
            topBadge: topBadge
        )
    }
}
