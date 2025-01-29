//
//  ICartViewControllerMock.swift
//  PROD-MobileTests
//
//  Created by Arina Kolganova on 17.01.2025.
//

import AppBase

final class CartViewControllerMock: ICartViewController {
    var invokedConfigureCartView = false
    var invokedConfigureCartViewCount = 0
    var invokedConfigureCartViewParameters: (totalCartViewModel: TotalCartViewModel, bonusViewModel: BonusViewModel)?

    func configureCartView(totalCartViewModel: TotalCartViewModel, bonusViewModel: BonusViewModel) {
        invokedConfigureCartView = true
        invokedConfigureCartViewCount += 1
        invokedConfigureCartViewParameters = (totalCartViewModel, bonusViewModel)
    }

    var invokedPush = false
    var invokedPushCount = 0
    var invokedPushParameters: UIViewController?

    func push(viewController: UIViewController) {
        invokedPush = true
        invokedPushCount += 1
        invokedPushParameters = viewController
    }
    
    var invokedDismiss = false
    var invokedDismissCount = 0.0
    func dismiss() {
        invokedDismiss = true
        invokedDismissCount += 1
    }
}
