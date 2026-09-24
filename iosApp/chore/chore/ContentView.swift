import App
import SwiftUI

struct ContentView: View {

    @State private var appNavigation = AppNavigation()

    var body: some View {
        NavigationStack(path: $appNavigation.path) {
            HomeRoute(
                onNavigateToChoreCreate: appNavigation.navigateToChoreCreate,
                onNavigateToChoreDetails: appNavigation.navigateToChoreDetails,
            )
            .navigationDestination(for: RouteKey.self) { key in
                switch key {
                case .home:
                    HomeRoute(
                        onNavigateToChoreCreate: appNavigation.navigateToChoreCreate,
                        onNavigateToChoreDetails: appNavigation.navigateToChoreDetails,
                    )
                case .choreCreate:
                    ChoreCreateRoute(
                        onFinish: { chore in
                            appNavigation.path[appNavigation.path.endIndex - 1] = .choreDetails(id: chore.id)
                        },
                    )
                case .choreDetails(let id):
                    ChoreDetailsRoute(choreId: id)
                }
            }
        }
    }
}

#Preview {
    ContentView()
}
