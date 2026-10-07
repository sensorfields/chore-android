import App
import SwiftUI

struct ContentView: View {

    @State private var appNavigation = AppNavigation()

    @State private var selectedTab: TabKey = .dashboard

    var body: some View {
        TabView(selection: $selectedTab) {
            Tab(.dashboard) {
                NavigationStack(path: $appNavigation.path) {
                    DashboardRoute(
                        onNavigateToChoreCreate: appNavigation.navigateToChoreCreate,
                        onNavigateToChoreDetails: appNavigation.navigateToChoreDetails,
                    )
                    .navigationDestination(for: RouteKey.self) { key in
                        switch key {
                        case .choreCreate:
                            ChoreCreateRoute(
                                onFinish: { chore in
                                    NSLog("Chore created: \(chore.name)")  // TODO toast
                                    appNavigation.navigateToHome()
                                },
                            )
                        case .choreDetails(let id):
                            ChoreDetailsRoute(choreId: id)
                        }
                    }
                }
            }
            Tab(.stats) {
                NavigationStack {
                    StatsRoute()
                }
            }
            Tab(.settings) {
                NavigationStack {
                    SettingsRoute()
                }
            }
        }
    }
}

enum TabKey: Equatable, Hashable, Identifiable {
    case dashboard
    case stats
    case settings

    var id: Self { self }

    var title: StringResource {
        switch self {
        case .dashboard: Res.string.shared.home_navigation_dashboard
        case .stats: Res.string.shared.home_navigation_stats
        case .settings: Res.string.shared.home_navigation_settings
        }
    }

    var systemImage: String {
        switch self {
        case .dashboard: "house"
        case .stats: "chart.bar"
        case .settings: "gearshape"
        }
    }
}

extension Tab {

    init(_ key: TabKey, @ContentBuilder content: () -> Content)
    where Label == DefaultTabLabel, Value == TabKey, Content: View {
        self.init(key.title.format(), systemImage: key.systemImage, value: key, content: content)
    }
}

#Preview {
    ContentView()
}
