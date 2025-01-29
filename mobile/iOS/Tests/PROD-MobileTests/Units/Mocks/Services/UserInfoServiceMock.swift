//
//  UserInfoServiceMock.swift
//  PROD-MobileTests
//
//  Created by a yatsenko on 07.01.2025.
//

import AppBase

final class UserInfoServiceMock: IUserInfoService {
  
    var invokedLoadUserInfo = false
    var invokedLoadUserInfoCount = 0
    var stubbedLoadUserInfoCompletionResult: Result<UserInfo, Error>?
    
    func loadUserInfo(completion: @escaping (Result<UserInfo, Error>) -> Void) {
        invokedLoadUserInfo = true
        invokedLoadUserInfoCount += 1
        if let result = stubbedLoadUserInfoCompletionResult {
            completion(result)
        }
    }
}
