import App
import SwiftUI

struct ChoreDetailsScreen: View {

    let state: ChoreDetailsState

    var body: some View {
        switch onEnum(of: state) {
        case .empty:
            Spacer()
        case .chore(let chore):
            VStack {
                Text(chore.name)
                Text(chore.date.format())
            }
        }
    }
}

#Preview {
    ChoreDetailsScreen(
        state: ChoreDetailsStateChore(
            name: "Some name",
            date: Date.distantFuture.toLocalDateTime(),
        ),
    )
}
