import SwiftUI

@Observable
class AppNavigation {
    var path: [RouteKey] = []

    func navigateToHome() {
        path.removeAll()
        path.append(.home)
    }

    func navigateToChoreCreate() {
        path.append(.choreCreate)
    }
}

enum RouteKey: Identifiable, Hashable, Codable {
    case home
    case choreCreate

    var id: Self { self }
}
