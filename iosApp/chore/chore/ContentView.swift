import SwiftUI

struct ContentView: View {

    @State private var appNavigation = AppNavigation()

    var body: some View {
        NavigationStack(path: $appNavigation.path) {
            HomeRoute(
                onNavigateToChoreCreate: appNavigation.navigateToChoreCreate,
            )
            .navigationDestination(for: RouteKey.self) { key in
                switch key {
                case .home:
                    HomeRoute(
                        onNavigateToChoreCreate: appNavigation.navigateToChoreCreate,
                    )
                case .choreCreate:
                    ChoreCreateRoute(
                        onFinish: { chore in
                            // TODO show snackbar
                            appNavigation.navigateBack()
                        },
                    )
                }
            }
        }
    }
}

#Preview {
    ContentView()
}
