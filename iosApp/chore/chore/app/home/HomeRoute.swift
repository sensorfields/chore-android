import SwiftUI

struct HomeRoute: View {

    let onNavigateToChoreCreate: () -> Void

    var body: some View {
        VStack {
            Text("Home")
            Button("Create chore", action: onNavigateToChoreCreate)
        }
    }
}

#Preview {
    HomeRoute(
        onNavigateToChoreCreate: {},
    )
}
