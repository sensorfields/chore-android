import App
import SwiftUI

struct ChoreCreateSummary: View {

    let state: ChoreCreateState

    var body: some View {
        ScrollView(.vertical) {
            VStack {
                Text(state.name)
                Text(state.name)
                Text(state.date.format())
                Text(state.time.format())
//                Text(state.daysOfWeek.map({ $0.name }).joined(separator: ","))
//                Text(state.daysOfMonth.map({ $0.stringValue }).joined(separator: ","))
//                Text(state.months.map({ $0.name }).joined(separator: ","))
            }
        }
    }
}

#Preview {
    ChoreCreateSummary(
        state: ChoreCreateState(
            step: .summary,
            name: "Some name",
            repeat: .once,
            date: Date.distantFuture.toLocalDate(),
            time: Date.distantFuture.toLocalTime(),
            daysOfWeek: [],
            daysOfMonth: [],
            months: [],
            isNextButtonEnabled: true,
            isLoadingVisible: false,
        ),
    )
}
