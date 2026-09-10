import App
import SwiftUI

struct HomeRoute: View {

    let onNavigateToChoreCreate: () -> Void
    let onNavigateToChoreDetails: (String) -> Void

    @State private var selectedTab: TabKey = .dashboard

    var body: some View {
        TabView(selection: $selectedTab) {
            Tab(Res.string.shared.home_navigation_dashboard.format(), systemImage: "house", value: .dashboard) {
                DashboardRoute(
                    onNavigateToChoreDetails: onNavigateToChoreDetails,
                )
            }
            Tab(Res.string.shared.home_navigation_stats.format(), systemImage: "chart.bar", value: .stats) {
                StatsRoute()
            }
            Tab(Res.string.shared.home_navigation_settings.format(), systemImage: "gearshape", value: .settings) {
                SettingsRoute()
            }
        }
        .toolbar {
            Button(
                Res.string.shared.dashboard_chore_create_button.format(),
                systemImage: "plus",
                action: onNavigateToChoreCreate,
            )
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
