import App
import Combine
import SwiftUI

struct ChoreCreateRoute: View {

    let onFinish: (DomainChore) -> Void

    @ObservedObject private var viewModel: ViewModel = ViewModel()

    var body: some View {
        ChoreCreateScreen(
            state: viewModel.state,
            name: $viewModel.name,
            date: $viewModel.date,
            time: $viewModel.time,
            onRepeatClick: viewModel.onRepeatClick,
            onNextClick: viewModel.onNextClick,
        ).task {
            await viewModel.state()
        }.task {
            await viewModel.actions { action in
                switch onEnum(of: action) {
                case .showError(let error):
                    NSLog("SHOW ERROR YOO: \(error)")
                case .finish(let finish):
                    onFinish(finish.chore)
                }
            }
        }
    }
}

extension ChoreCreateRoute {
    class ViewModel: ObservableObject {

        private let vm: ChoreCreateViewModel

        @Published var state: ChoreCreateState

        var name: String {
            get { state.whatName }
            set { vm.onNameChange(name: newValue) }
        }
        var date: Date {
            get { state.whenDate }
            set { vm.onDateChange(date: newValue.toLocalDate()) }
        }
        var time: Date {
            get { state.whenTime }
            set { vm.onTimeChange(time: newValue.toLocalTime()) }
        }

        init() {
            vm = appGraph.choreCreateViewModel
            state = vm.state.value
        }

        func state() async {
            for await state in vm.state {
                self.state = state
            }
        }

        func actions(verbatim onAction: (ChoreCreateAction) -> Void) async {
            for await action in vm.actions {
                onAction(action)
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
    var whenDate: Date {
        switch onEnum(of: self) {
        case .whenDate(let whenDate):
            return whenDate.date.toDate()
        default:
            return Date.distantPast
        }
    }
    var whenTime: Date {
        switch onEnum(of: self) {
        case .whenTime(let whenTime):
            return whenTime.time.toDate()
        default:
            return Date.distantPast
        }
    }
}
