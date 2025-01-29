//
//  ImageFactoryMock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 02.01.2025.
//

import UIKit
import ProdMobileCore

final class ImageFactoryMock: IImageFactory {
    
    var invokedLargeBannerImage = false
    var invokedLargeBannerImageCount = 0
    var invokedLargeBannerImageParameters: (id: String, Void)?
    var invokedLargeBannerImageParametersList = [(id: String, Void)]()
    var stubbedLargeBannerImageResult = UIImage()
    
    func largeBannerImage(id: String) -> UIImage {
        invokedLargeBannerImage = true
        invokedLargeBannerImageCount += 1
        invokedLargeBannerImageParameters = (id, ())
        invokedLargeBannerImageParametersList.append((id, ()))
        return stubbedLargeBannerImageResult
    }
    
    var invokedSmallBannerImage = false
    var invokedSmallBannerImageCount = 0
    var stubbedSmallBannerImageResult = UIImage()
    
    func smallBannerImage() -> UIImage {
        invokedSmallBannerImage = true
        invokedSmallBannerImageCount += 1
        return stubbedSmallBannerImageResult
    }
    
    var invokedGoodsItemImage = false
    var invokedGoodsItemImageCount = 0
    var invokedGoodsItemImageParameters: (id: String, Void)?
    var invokedGoodsItemImageParametersList = [(id: String, Void)]()
    var stubbedGoodsItemImageResult = UIImage()
    
    func goodsItemImage(id: String) -> UIImage {
        invokedGoodsItemImage = true
        invokedGoodsItemImageCount += 1
        invokedGoodsItemImageParameters = (id, ())
        invokedGoodsItemImageParametersList.append((id, ()))
        return stubbedGoodsItemImageResult
    }
}
