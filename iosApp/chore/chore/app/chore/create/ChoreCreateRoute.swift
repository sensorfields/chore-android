import App
import Combine
import SwiftUI

struct ChoreCreateRoute: View {

    @ObservedObject var viewModel: ViewModel = ViewModel()

    var body: some View {
        ChoreCreateScreen(
            state: viewModel.state,
            name: $viewModel.name,
            date: $viewModel.date,
            time: $viewModel.time,
            onRepeatClick: viewModel.onRepeatClick,
            onNextClick: viewModel.onNextClick,
        ).task {
            await viewModel.observe()
        }
    }
}

extension ChoreCreateRoute {
    class ViewModel: ObservableObject {

        private let vm: ChoreCreateViewModel

        @Published var state: ChoreCreateState

        var name: String {
            get { return state.whatName }
            set { vm.onNameChange(name: newValue) }
        }
        var date: Date {
            get { Date.now }
            set {}
        }
        var time: Date {
            get { Date.now }
            set {}
        }

        init() {
            vm = appGraph.choreCreateViewModel
            state = vm.state.value
        }

        func observe() async {
            for await state in vm.state {
                self.state = state
            }
        }

        func onRepeatClick(repeatValue: ChoreCreateState.When.WhenRepeat) {
            vm.onRepeatClick(repeat: repeatValue)
        }

        func onNextClick() {
            vm.onNextClick()
        }
    }
}

extension ChoreCreateState {
    var whatName: String {
        switch onEnum(of: self) {
        case .what(let what):
            return what.name
        default:
            return ""
        }
    }
}

#Preview {
    ChoreCreateRoute()
}
