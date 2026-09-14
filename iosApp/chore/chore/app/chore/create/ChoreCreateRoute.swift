import App
import Combine
import SwiftUI

struct ChoreCreateRoute: View {

    let onFinish: (Chore) -> Void

    @ObservedObject private var viewModel: ViewModel = ViewModel()

    var body: some View {
        ChoreCreateScreen(
            state: viewModel.state,
            name: $viewModel.name,
            date: $viewModel.date,
            time: $viewModel.time,
            daysOfWeek: $viewModel.daysOfWeek,
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
            get { state.name }
            set { vm.onNameChange(name: newValue) }
        }
        var date: Date {
            get { state.date.toDate() }
            set { vm.onDateChange(date: newValue.toLocalDate()) }
        }
        var time: Date {
            get { state.time.toDate() }
            set { vm.onTimeChange(time: newValue.toLocalTime()) }
        }
        var daysOfWeek: [SelectableItem<DayOfWeek>] {
            get {
                state.daysOfWeek.map { item in
                    SelectableItem(value: item.value! as DayOfWeek, isOn: item.selected)
                }
            }
            set {
                newValue.forEach { item in
                    vm.onDayOfWeekCheckedChange(day: item.value, checked: item.isOn)
                }
            }
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

        func onRepeatClick(repeatValue: ChoreCreateState.Repeat) {
            vm.onRepeatClick(repeat: repeatValue)
        }

        func onNextClick() {
            vm.onNextClick()
        }
    }
}
