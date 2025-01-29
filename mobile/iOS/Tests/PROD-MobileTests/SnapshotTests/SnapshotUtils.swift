//
//  SnapshotUtils.swift
//  PROD-MobileTests
//
//  Created by Kuznetsov Mikhail on 27.12.2023.
//

import UIKit
import FBSnapshotTestCase

extension FBSnapshotTestCase {
    func verify(
        view: UIView,
        file: StaticString = #file,
        line: UInt = #line
    ) {
        FBSnapshotVerifyView(
            view,
            perPixelTolerance: 0.02,
            file: file,
            line: line
        )
    }
}
