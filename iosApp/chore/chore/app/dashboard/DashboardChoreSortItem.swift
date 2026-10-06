import App
import SwiftUI

struct DashboardChoreSortItem: View {

    let sort: DashboardState.ChoreSort
    let sortProperty: Chore.SortProperty
    let onClick: @MainActor (Chore.SortProperty) -> Void

    var body: some View {
        Button {
            onClick(sortProperty)
        } label: {
            if sort.sortBy == sortProperty {
                Label(
                    sortProperty.format(),
                    systemImage: "checkmark",
                )
                let direction =
                    if sort.isAscending {
                        Res.string.shared.dashboard_chore_sort_ascending
                    } else {
                        Res.string.shared.dashboard_chore_sort_descending
                    }
                Text(direction.format())
            } else {
                Text(sortProperty.format())
            }
        }
    }
}

#Preview {
    VStack {
        DashboardChoreSortItem(
            sort: .init(sortBy: .name, isAscending: true),
            sortProperty: .name,
            onClick: { _ in },
        )
        DashboardChoreSortItem(
            sort: .init(sortBy: .name, isAscending: true),
            sortProperty: .date,
            onClick: { _ in },
        )
    }
    .buttonStyle(.bordered)
}
