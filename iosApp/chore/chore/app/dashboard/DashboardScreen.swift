import App
import SwiftUI

struct DashboardScreen: View {

    let state: DashboardState

    var body: some View {
        List(state.choreItems, id: \.id) { item in
            DashboardChoreItem(state: item)
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
        )
    )
}
