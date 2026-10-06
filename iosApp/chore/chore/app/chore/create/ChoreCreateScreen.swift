import App
import SwiftUI

struct ChoreCreateScreen: View {

    let state: ChoreCreateState
    @Binding var name: String
    @Binding var date: Date
    @Binding var time: Date
    @Binding var daysOfWeek: [SelectableItem<DayOfWeek>]
    @Binding var daysOfMonth: [SelectableItem<Int>]
    @Binding var months: [SelectableItem<Month>]
    let onRepeatClick: (ChoreCreateState.Repeat) -> Void
    let onNextClick: () -> Void

    var body: some View {
        Group {
            switch state.step {
            case .what:
                ChoreCreateWhat(name: $name)
            case .when:
                ChoreCreateWhen(onRepeatClick: onRepeatClick)
            case .whenDate:
                ChoreCreateWhenDate(date: $date)
            case .whenTime:
                ChoreCreateWhenTime(time: $time)
            case .whenWeek:
                ChoreCreateWhenWeek(items: $daysOfWeek)
            case .whenMonth:
                ChoreCreateWhenMonth(items: $daysOfMonth)
            case .whenYear:
                ChoreCreateWhenYear(items: $months)
            case .summary:
                ChoreCreateSummary(state: state)
            }
        }
        .navigationTitle(Res.string.shared.chore_create_title.format())
        .toolbarVisibility(Visibility.hidden, for: .tabBar)
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
        daysOfWeek: Binding.constant([]),
        daysOfMonth: Binding.constant([]),
        months: Binding.constant([]),
        onRepeatClick: { _ in },
        onNextClick: {},
    )
}
