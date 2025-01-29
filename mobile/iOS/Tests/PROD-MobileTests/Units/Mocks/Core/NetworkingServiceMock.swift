//
//  NetworkingServiceMock.swift
//  PROD-Mobile
//
//  Created by Kuznetsov Mikhail on 17.01.2025.
//

import Foundation
import ProdMobileCore

final class NetworkingServiceMock: INetworkingService {

    var invokedLoad = false
    var invokedLoadCount = 0
    var invokedLoadParameters: (request: ProdMobileCore.IRequest, Void)?
    var invokedLoadParametersList = [(request: ProdMobileCore.IRequest, Void)]()
    var stubbedLoadCompletionResult: Result<Data, Error>?

    func load(request: ProdMobileCore.IRequest, _ completion: @escaping (Result<Data, Error>) -> ()) {
        invokedLoad = true
        invokedLoadCount += 1
        invokedLoadParameters = (request, ())
        invokedLoadParametersList.append((request, ()))
        if let result = stubbedLoadCompletionResult {
            completion(result)
        }
    }
}
