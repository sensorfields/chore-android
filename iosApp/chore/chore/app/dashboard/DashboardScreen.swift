import App
import SwiftUI

struct DashboardScreen: View {

    let state: DashboardState
    let onChoreSortClick: (Chore.SortProperty) -> Void
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
            ToolbarSpacer()
            ToolbarItem {
                Menu {
                    DashboardChoreSortItem(
                        sort: state.choreSort,
                        sortProperty: .name,
                        onClick: onChoreSortClick,
                    )
                    DashboardChoreSortItem(
                        sort: state.choreSort,
                        sortProperty: .date,
                        onClick: onChoreSortClick,
                    )
                } label: {
                    Label(
                        Res.string.shared.dashboard_sort_button.format(),
                        systemImage: "ellipsis",
                    )
                }
            }
        }
    }
}

#Preview {
    NavigationStack {
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
            onChoreSortClick: { _ in },
            onCreateChoreClick: {},
            onChoreClick: { _ in },
        )
    }
}
