import SwiftUI

struct HomeRoute: View {

    let onNavigateToChoreCreate: () -> Void
    let onNavigateToChoreDetails: (String) -> Void

    @State private var selectedTab: TabKey = .dashboard

    var body: some View {
        TabView(selection: $selectedTab) {
            Tab("Dashboard", systemImage: "house", value: .dashboard) {
                DashboardRoute(
                    onNavigateToChoreDetails: onNavigateToChoreDetails,
                )
            }
            Tab("Stats", systemImage: "chart.bar", value: .stats) {
                StatsRoute()
            }
            Tab("Settings", systemImage: "gearshape", value: .settings) {
                SettingsRoute()
            }
        }
        .toolbar {
            Button("Create Chore", systemImage: "plus", action: onNavigateToChoreCreate)
        }
    }
}

enum TabKey: Equatable, Hashable, Identifiable {
    case dashboard
    case stats
    case settings

    var id: Self { self }
}

#Preview {
    HomeRoute(
        onNavigateToChoreCreate: {},
        onNavigateToChoreDetails: { _ in },
    )
}
