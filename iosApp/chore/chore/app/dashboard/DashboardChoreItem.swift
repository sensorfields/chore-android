import App
import SwiftUI

struct DashboardChoreItem: View {

    let state: DashboardState.ChoreItem
    let onClick: () -> Void

    var body: some View {
        Button(action: onClick) {
            VStack(alignment: .leading) {
                Text(state.name)
                Text(state.date.format())
            }
            Spacer()
        }
        .foregroundStyle(.primary)
    }
}

#Preview {
    DashboardChoreItem(
        state: DashboardState.ChoreItem(
            id: "one",
            name: "Some name",
            date: Date.distantPast.toLocalDateTime(),
        ),
        onClick: {},
    )
}
