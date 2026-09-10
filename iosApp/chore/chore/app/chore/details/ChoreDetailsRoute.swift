import App
import Combine
import SwiftUI

struct ChoreDetailsRoute: View {

    let choreId: String

    @ObservedObject var viewModel: ViewModel

    init(choreId: String) {
        self.choreId = choreId
        self.viewModel = ViewModel(choreId: choreId)
    }

    var body: some View {
        ChoreDetailsScreen(state: viewModel.state)
            .task {
                await viewModel.state()
            }
    }
}

extension ChoreDetailsRoute {
    class ViewModel: ObservableObject {

        private let vm: ChoreDetailsViewModel

        @Published var state: ChoreDetailsState

        init(choreId: String) {
            vm = appGraph.choreDetailsViewModelFactory.create(choreId: choreId)
            state = vm.state.value
        }

        func state() async {
            for await state in vm.state {
                self.state = state
            }
        }
    }
}
