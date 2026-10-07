import SwiftUI

@Observable class AppNavigation {

    var path: [RouteKey] = []

    func navigateBack() {
        path.removeLast()
    }

    func navigateToHome() {
        path.removeAll()
    }

    func navigateToChoreCreate() {
        path.append(.choreCreate)
    }

    func navigateToChoreDetails(id: String) {
        path.append(.choreDetails(id: id))
    }
}

enum RouteKey: Identifiable, Hashable, Codable {
    case choreCreate
    case choreDetails(id: String)

    var id: Self { self }
}
