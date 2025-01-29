//
//  PersistenceStorageMock.swift
//  PROD-Mobile
//
//  Created by Kuznetsov Mikhail on 17.01.2025.
//

import Foundation
import ProdMobileCore

final class PersistenceStorageMock: IPersistenceStorage {

    var invokedSave = false
    var invokedSaveCount = 0
    var invokedSaveParameters: (item: Any, Void)?
    var invokedSaveParametersList = [(item: Any, Void)]()

    func save<Model>(_ item: Model) where Model : ProdMobileCore.Identifiable {
        invokedSave = true
        invokedSaveCount += 1
        invokedSaveParameters = (item, ())
        invokedSaveParametersList.append((item, ()))
    }

    var invokedFetch = false
    var invokedFetchCount = 0
    var invokedFetchParameters: (itemType: Any, Void)?
    var invokedFetchParametersList = [(itemType: Any, Void)]()
    var stubbedFetchResultList: [[Any]]! = []
    var stubbedFetchResult: [Any]! = []

    func fetch<Model>(_ itemType: Model.Type) -> [Model] where Model : ProdMobileCore.Identifiable {
        invokedFetch = true
        invokedFetchCount += 1
        invokedFetchParameters = (itemType, ())
        invokedFetchParametersList.append((itemType, ()))
        
        let indexResult = stubbedFetchResultList.firstIndex { result in
            (result as? [Model]) != nil
        }
        
        if let indexResult {
            let result = stubbedFetchResultList.remove(at: indexResult)
            
            return (result as? [Model]) ?? []
        }
        
        return (stubbedFetchResult as? [Model]) ?? []
    }

    var invokedDelete = false
    var invokedDeleteCount = 0
    var invokedDeleteParameters: (item: Any, Void)?
    var invokedDeleteParametersList = [(item: Any, Void)]()

    func delete<Model>(_ item: Model) where Model : ProdMobileCore.Identifiable {
        invokedDelete = true
        invokedDeleteCount += 1
        invokedDeleteParameters = (item, ())
        invokedDeleteParametersList.append((item, ()))
    }
}
