import SwiftUI

struct ChoreCreateRoute: View {

    @State private var text: String = ""

    var body: some View {
        VStack {
            ScrollView(.vertical) {
                TextField(text: $text) {
                    Text("Name")
                }
            }
            Group {
                Button("Continue") {
                }.buttonStyle(BorderedProminentButtonStyle())
            }
        }
    }
}

#Preview {
    ChoreCreateRoute()
}
