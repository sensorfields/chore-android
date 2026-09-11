import App
import SwiftUI

struct ChoreCreateScreen: View {

    let state: ChoreCreateState
    @Binding var name: String
    @Binding var date: Date
    @Binding var time: Date
    let onRepeatClick: (ChoreCreateState.When.WhenRepeat) -> Void
    let onNextClick: () -> Void

    var body: some View {
        Group {
            switch onEnum(of: state) {
            case .what:
                ChoreCreateWhat(name: $name)
            case .when:
                ChoreCreateWhen(onRepeatClick: onRepeatClick)
            case .whenDate:
                ChoreCreateWhenDate(date: $date)
            case .whenTime:
                ChoreCreateWhenTime(time: $time)
            case .whenWeek:
                ChoreCreateWhenWeek()
            case .whenMonth:
                Text("WHEN MONTH")
            case .whenYear:
                Text("WHEN YEAR")
            case .summary(let summary):
                ChoreCreateSummary(state: summary)
            }
        }
        .safeAreaInset(edge: .bottom) {
            Button(Res.string.shared.chore_create_next_button.format(), action: onNextClick)
                .disabled(!state.isNextButtonEnabled)
                .buttonStyle(BorderedProminentButtonStyle())
        }
    }
}

#Preview {
    ChoreCreateScreen(
        state: ChoreCreateState.companion.initial(),
        name: Binding.constant(""),
        date: Binding.constant(Date()),
        time: Binding.constant(Date()),
        onRepeatClick: { _ in },
        onNextClick: {},
    )
}
