import App
import Combine
import SwiftUI

struct DashboardRoute: View {

    let onNavigateToChoreCreate: () -> Void
    let onNavigateToChoreDetails: (String) -> Void

    @State private var viewModel: ViewModel = ViewModel()

    var body: some View {
        DashboardScreen(
            state: viewModel.state,
            onChoreSortClick: viewModel.onChoreSortClick,
            onCreateChoreClick: onNavigateToChoreCreate,
            onChoreClick: onNavigateToChoreDetails,
        ).task {
            await viewModel.state()
        }.task {
            await viewModel.actions { action in
                NSLog("AAAAAAAAA ACTION: \(action)")
            }
        }
    }
}

extension DashboardRoute {
    @Observable class ViewModel {

        private let vm: DashboardViewModel

        var state: DashboardState

        init() {
            self.vm = appGraph.dashboardViewModel
            self.state = vm.state.value
        }

        func state() async {
            for await state in vm.state {
                self.state = state
            }
        }

        func actions(verbatim onAction: (DashboardAction) -> Void) async {
            for await action in vm.actions {
                onAction(action)
            }
        }

        func onChoreSortClick(sortProperty: Chore.SortProperty) {
            vm.onChoreSortByClick(sortBy: sortProperty)
        }
    }
}
