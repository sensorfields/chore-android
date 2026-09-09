import App
import SwiftUI

struct ChoreCreateRoute: View {

    private let viewModel: ChoreCreateViewModel = appGraph.choreCreateViewModel

    @State private var state: ChoreCreateState = ChoreCreateState.companion.initial()

    var body: some View {
        VStack {
            ScrollView(.vertical) {
                switch onEnum(of: state) {
                case .what(let what):
                    ChoreCreateWhat(
                        name: what.name,
                        onNameChange: viewModel.onNameChange,
                    )
                case .when:
                    ChoreCreateWhen(onRepeatClick: viewModel.onRepeatClick)
                case .whenDate:
                    ChoreCreateWhenDate()
                case .whenTime:
                    ChoreCreateWhenTime()
                case .whenWeek:
                    Text("WHEN WEEK")
                case .whenMonth:
                    Text("WHEN MONTH")
                case .whenYear:
                    Text("WHEN YEAR")
                case .summary:
                    Text("SUMMARY")
                }
            }
            Group {
                Button("Continue", action: viewModel.onNextClick)
                    .disabled(!state.isNextButtonEnabled)
                    .buttonStyle(BorderedProminentButtonStyle())
            }
        }.task {
            for await state in viewModel.state {
                self.state = state
            }
        }
    }
}

#Preview {
    ChoreCreateRoute()
}
