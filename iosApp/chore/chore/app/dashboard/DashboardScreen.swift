import App
import SwiftUI

struct DashboardScreen: View {

    let state: DashboardState
    let onCreateChoreClick: () -> Void
    let onChoreClick: (String) -> Void

    @State private var selectedChore: String?

    var body: some View {
        List(state.choreItems, id: \.id) { item in
            DashboardChoreItem(
                state: item,
                onClick: { onChoreClick(item.id) },
            )
        }
        .navigationTitle(Res.string.shared.dashboard_title.format())
        .toolbar {
            ToolbarItem {
                Button(
                    Res.string.shared.dashboard_chore_create_button.format(),
                    systemImage: "plus",
                    action: onCreateChoreClick,
                )
            }
        }
    }
}

#Preview {
    DashboardScreen(
        state: DashboardState(
            choreSort: DashboardState.ChoreSort(
                sortBy: .name,
                isAscending: true,
            ),
            choreItems: [
                DashboardState.ChoreItem(
                    id: "one",
                    name: "Some name",
                    date: Date.distantPast.toLocalDateTime(),
                ),
                DashboardState.ChoreItem(
                    id: "two",
                    name: "Some other name",
                    date: Date.distantFuture.toLocalDateTime(),
                ),
            ],
        ),
        onCreateChoreClick: {},
        onChoreClick: { _ in },
    )
}
