import App
import SwiftUI

struct DashboardChoreItem: View {

    let state: DashboardState.ChoreItem

    var body: some View {
        VStack(alignment: .leading) {
            Text(state.name)
            Text(state.date.format())
        }
    }
}

#Preview {
    DashboardChoreItem(
        state: DashboardState.ChoreItem(
            id: "one",
            name: "Some name",
            date: Date.distantPast.toLocalDateTime(),
        ),
    )
}
