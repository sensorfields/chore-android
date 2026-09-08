import SwiftUI

struct HomeRoute: View {

    let onNavigateToChoreCreate: () -> Void

    @State private var selectedTab: TabKey = .dashboard

    var body: some View {
        TabView(selection: $selectedTab) {
            Tab("Dashboard", systemImage: "house", value: .dashboard) {
                DashboardRoute()
            }
            Tab("Stats", systemImage: "chart.bar", value: .stats) {
                StatsRoute()
            }
            Tab("Settings", systemImage: "gearshape", value: .settings) {
                SettingsRoute()
            }
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
    )
}
