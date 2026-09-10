import App
import SwiftUI

struct ChoreCreateSummary: View {

    let state: ChoreCreateState.Summary

    var body: some View {
        VStack {
            Text(state.name)
            Text(state.repeat.name)
            Text(state.date.format())
            Text(state.time.format())
            Text(state.daysOfWeek.map({ $0.name }).joined(separator: ","))
            Text(state.daysOfMonth.map({ $0.stringValue }).joined(separator: ","))
            Text(state.months.map({ $0.name }).joined(separator: ","))
        }
    }
}

#Preview {
    ChoreCreateSummary(
        state: ChoreCreateState.Summary(
            name: "Some name",
            repeat: .once,
            date: Date.distantFuture.toLocalDate(),
            time: Date.distantFuture.toLocalTime(),
            daysOfWeek: [.wednesday, .friday],
            daysOfMonth: [15, 25],
            months: [.february, .november],
            isLoadingVisible: false,
        ),
    )
}
